package com.bellminp.data

import com.bellminp.domain.model.DomainExchange
import com.bellminp.domain.repository.RemoteRepository
import retrofit2.Response

class FakeRemoteRepositoryImpl : RemoteRepository {
    override suspend fun getExchange(recipientCountry: String): Response<DomainExchange> {
        return Response.success(250, DomainExchange(
            true,
            null,
            null,
            1670257923,
            "USD",
            1412.232345,
            143.123123,
            55.12341,
            null,
            null
        )
        )
    }
}