package com.bellminp.domain.usecase

import com.bellminp.domain.model.ApiResult
import retrofit2.Response

open class BaseUseCase {
    companion object{
        private const val SUCCESS_CODE = "0"
    }


    internal fun <T> getResponse(response: Response<T>): ApiResult<T> {
        return try {
            if (response.isSuccessful) {
                return ApiResult.success(SUCCESS_CODE, response.body())
            } else {
                val code = response.code()
                val message = response.message()
                ApiResult.error(code, message)
            }
        } catch (e: Exception) {
            ApiResult.error(e)
        }
    }
}