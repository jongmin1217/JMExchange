package com.bellminp.common

import android.util.Log
import timber.log.Timber

fun timberMsg(vararg msg : Any?){
    var text = ""
    for(i in msg){
        text +=" $i"
    }
    Timber.d("Timber Message $text")
}