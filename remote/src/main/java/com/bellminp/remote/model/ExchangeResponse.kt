package com.bellminp.remote.model

import com.google.gson.annotations.SerializedName

data class ExchangeResponse(
    val success : Boolean,
    val terms : String?,
    val privacy : String?,
    val timestamp : Long?,
    val source : String?,
    val quotes : RemoteExchange?,
    val error : ErrorData?
)

data class ErrorData(
    val code : Int,
    val info : String?,
)

data class RemoteExchange(
    @SerializedName("USDKRW")
    val usdKrw : Double?,
    @SerializedName("USDJPY")
    val usdJpy : Double?,
    @SerializedName("USDPHP")
    val usdPhp : Double?
)