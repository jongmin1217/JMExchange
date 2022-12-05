package com.bellminp.data.model

data class DataExchange(
    val success : Boolean,
    val terms : String?,
    val privacy : String?,
    val timestamp : Long?,
    val source : String?,
    val krw : Double?,
    val jpy : Double?,
    val php : Double?,
    val errorCode : Int?,
    val errorInfo : String?
)