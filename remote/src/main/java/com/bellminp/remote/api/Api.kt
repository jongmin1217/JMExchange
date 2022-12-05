package com.bellminp.remote.api

import com.bellminp.remote.model.ExchangeResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api {

    @GET("/currency_data/live")
    @Throws(Exception::class)
    suspend fun getExchange(
        @Query("currencies") currencies : String,
        @Header("apikey") apikey: String
    ): Response<ExchangeResponse>

//    @GET("/api/live")
//    suspend fun getExchange(
//        @Query("access_key") access_key: String
//    ): Response<ExchangeResponse>

}