package com.bellminp.data.remote

import com.bellminp.data.model.DataExchange
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getExchange(recipientCountry : String) : Response<DataExchange>
}