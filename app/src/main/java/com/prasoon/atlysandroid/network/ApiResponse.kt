package com.prasoon.atlysandroid.network

import com.prasoon.atlysandroid.data.Resource
import retrofit2.Response

object ApiResponse {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): Resource<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return Resource.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): Resource<T> =
        Resource.Error("Api call failed $errorMessage")
}