package com.bellminp.domain.usecase

import com.bellminp.domain.model.ApiResult
import com.bellminp.domain.model.DomainExchange
import com.bellminp.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository
) : BaseUseCase() {

    suspend fun getExchange(recipientCountry : String): Flow<ApiResult<DomainExchange>> {
        return flow {
            val result = getResponse(remoteRepository.getExchange(recipientCountry))
            emit(result)
        }
    }
}