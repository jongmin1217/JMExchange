package com.bellminp.jmexchange.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bellminp.jmexchange.utils.Event

abstract class BaseViewModel : ViewModel() {

    private val _toast = MutableLiveData<Event<String>>()
    val toast: LiveData<Event<String>>
        get() = _toast

    protected fun showToast(vararg msg : Any?){
        var text = ""
        for(i in msg){
            text +=" $i"
        }
        _toast.value = Event(text)
    }
}