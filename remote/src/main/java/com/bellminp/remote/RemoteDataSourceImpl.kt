package com.bellminp.remote

import com.bellminp.data.model.DataExchange
import com.bellminp.data.remote.RemoteDataSource
import com.bellminp.remote.api.Api
import com.bellminp.remote.mapper.exchangeToData
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val api: Api
) : RemoteDataSource{

    companion object{
        const val SUCCESS = 0
        const val FAIL = 1
        const val ERROR = 2
    }


    override suspend fun getExchange(recipientCountry : String): Response<DataExchange> {
        val response = api.getExchange(recipientCountry,BuildConfig.API_KEY)
        //val response = api.getExchange(BuildConfig.TEST_KEY)
        return when(getResponse(response)){
            SUCCESS -> Response.success(response.code(),response.body()?.exchangeToData())
            FAIL -> Response.error(response.code(), response.errorBody() ?: "error".toResponseBody(null))
            else -> Response.error(500, response.errorBody() ?: "error".toResponseBody(null))
        }
    }

    private fun <T> getResponse(response: Response<T>): Int {
        return try {
            if (response.isSuccessful) SUCCESS
            else FAIL
        } catch (e: Exception) {
            ERROR
        }
    }
}