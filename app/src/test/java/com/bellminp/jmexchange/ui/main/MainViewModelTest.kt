package com.bellminp.jmexchange.ui.main

import com.bellminp.common.timberMsg
import com.bellminp.domain.usecase.RemoteUseCase
import com.bellminp.jmexchange.BaseUnitTest
import com.bellminp.jmexchange.getOrAwaitValue
import com.bellminp.jmexchange.repository.FakeRepository
import com.bellminp.jmexchange.utils.roundDownDigit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test
import java.text.DecimalFormat
import kotlin.random.Random

@ExperimentalCoroutinesApi
class MainViewModelTest : BaseUnitTest(){
    private val random = Random.nextDouble()
    private val remoteUseCase = RemoteUseCase(FakeRepository(random))

    private lateinit var viewModel: MainViewModel

    override fun setup() {
        viewModel = MainViewModel(remoteUseCase)
    }

    @Test
    fun shouldDisplayExchange() {

        viewModel.networkConnected = true

        val dec = DecimalFormat("#,##0.00")
        for(i in 0..100){
            viewModel.selectCountry.value = 1
            viewModel.remittanceAmount.value = "$i"
            viewModel.getExchange("KRW")

            assertEquals(viewModel.exchangeDataTest.getOrAwaitValue()?.exchange,"${dec.format(roundDownDigit(random,2))} KRW")
            assertEquals(viewModel.exchangeDataTest.getOrAwaitValue()?.recipientAmount,"${dec.format(roundDownDigit(random*i,2))} KRW")
        }
    }


}