package com.bellminp.domain.repository

import com.bellminp.domain.model.DomainExchange
import retrofit2.Response

interface RemoteRepository {

    suspend fun getExchange(recipientCountry : String) : Response<DomainExchange>
}