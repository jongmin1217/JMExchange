package com.bellminp.jmexchange.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import kotlin.math.floor
import kotlin.math.pow

@SuppressLint("SimpleDateFormat")
fun convertTimestampToDateTerm(timestamp: Long): String =
    SimpleDateFormat("yyyy-MM-dd HH:mm").format(timestamp)

fun roundDownDigit(number : Double, digits : Int): Double {
    return floor(number * 10.0.pow(digits.toDouble())) / 10.0.pow(digits.toDouble())
}

fun checkRemittanceAmount(text : String) = try {
    val number = text.toDouble()
    !(number<0 || number > 10000)
} catch (e: NumberFormatException) {
    text.isEmpty()
}