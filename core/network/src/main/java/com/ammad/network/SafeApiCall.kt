package com.ammad.network

import retrofit2.Response
import kotlin.coroutines.cancellation.CancellationException

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResult<T> = try {
    val response = apiCall()
    if (response.isSuccessful) {
        val body = response.body()
        if (body != null) {
            ApiResult.Success(body)
        } else {
            ApiResult.Error(Exception("Response body is null"))
        }
    } else {
        val errorBody = response.errorBody()?.string()
        ApiResult.Error(Exception(errorBody ?: response.message()))
    }
} catch (e: CancellationException) {
    throw e
} catch (e: Exception) {
    ApiResult.Error(e)
}