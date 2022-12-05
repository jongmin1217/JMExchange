package com.bellminp.data

import com.bellminp.data.mapper.dataToDomain
import com.bellminp.data.remote.RemoteDataSource
import com.bellminp.domain.model.DomainExchange
import com.bellminp.domain.repository.RemoteRepository
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : RemoteRepository {

    override suspend fun getExchange(recipientCountry : String): Response<DomainExchange> {
        val response = remoteDataSource.getExchange(recipientCountry)
        return if(response.isSuccessful){
            Response.success(response.code(),response.body()?.dataToDomain())
        }else{
            Response.error(response.code(), response.errorBody() ?: ResponseBody.create(null,"error"))
        }
    }
}