package com.bellminp.jmexchange.repository

import com.bellminp.domain.model.DomainExchange
import com.bellminp.domain.repository.RemoteRepository
import retrofit2.Response
import kotlin.random.Random

class FakeRepository(private val value : Double) : RemoteRepository {
    override suspend fun getExchange(recipientCountry: String): Response<DomainExchange> {
        return Response.success(250, DomainExchange(
            true,
            null,
            null,
            1670257923,
            "USD",
            value,
            value,
            value,
            null,
            null
        )
        )
    }
}