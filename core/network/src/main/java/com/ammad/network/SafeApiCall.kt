package com.ammad.network

import android.util.Log
import android.util.Log.e
import retrofit2.Response

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
        ApiResult.Error(Exception(response.message()))
    }
} catch (e: Exception) {
    ApiResult.Error(e)
}