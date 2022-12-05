package com.bellminp.jmexchange.ui.main

import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.bellminp.common.timberMsg
import com.bellminp.domain.model.ApiResult
import com.bellminp.domain.usecase.RemoteUseCase
import com.bellminp.jmexchange.R
import com.bellminp.jmexchange.mapper.mapToPresentation
import com.bellminp.jmexchange.model.ExchangeData
import com.bellminp.jmexchange.ui.base.BaseViewModel
import com.bellminp.jmexchange.utils.DelegateLiveData
import com.bellminp.jmexchange.utils.RECIPIENT_LIST
import com.bellminp.jmexchange.utils.checkRemittanceAmount
import com.bellminp.jmexchange.utils.ext.default
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteUseCase: RemoteUseCase
) : BaseViewModel() {

    private var timer = Timer()
    private var currencies = RECIPIENT_LIST[0]
    var networkConnected = false

    val straightRemittanceAmount = MutableLiveData<Boolean>().default(true)

    val exchangeData = MutableLiveData<ExchangeData>().default(null)
    val exchangeDataTest = exchangeData.switchMap {
        liveData(viewModelScope.coroutineContext) {
            it?.let {
                emit(it)
            } ?: run {
                emit(null)
            }
        }
    }

    val remittanceAmount = DelegateLiveData<String> { old, new ->
        if (old != new){
            straightRemittanceAmount.value = checkRemittanceAmount(new)
            initTimer()
        }
    }.default("")

    val selectCountry = DelegateLiveData<Int> { old, new ->
        if (old != new){
            currencies = RECIPIENT_LIST[new]
            initTimer()
        }
    }.default(0)



    private fun initTimer() {
        timer.cancel()
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                getExchange(currencies)
            }
        }, 200)
    }

    fun getExchange(type: String) {
        if(networkConnected && straightRemittanceAmount.value == true){
            viewModelScope.launch {
                remoteUseCase.getExchange(type).collect {
                    when(it.status){
                        ApiResult.Status.SUCCESS -> {
                            it.data?.let { data ->
                                if (data.success) {
                                    exchangeData.value = data.mapToPresentation(
                                        selectCountry.value ?: 0,
                                        if (remittanceAmount.value?.isNotEmpty() == true) remittanceAmount.value?.toDouble() ?: 0.0
                                        else 0.0,
                                        currencies
                                    )
                                }else{
                                    showToast("code : ${data.errorCode}, info : ${data.errorInfo}")
                                }
                            }
                        }

                        ApiResult.Status.API_ERROR -> showToast("API ERROR")

                        else -> showToast("NETWORK ERROR")
                    }
                }
            }
        }
    }
}