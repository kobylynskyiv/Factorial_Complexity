package com.kobylynskyiv.data.helper

import retrofit2.Response

sealed class ApiResponseExtensions<T> {
    companion object {
        operator fun <T> invoke(response: Response<T>): ApiResponseExtensions<T> {
            return try {
                val body = response.body()

                if (response.code() == SUCCESS && response.isSuccessful && body != null) ApiSuccessResponseExtensions(body)
                else ApiErrorResponseExtensions(response.errorBody()?.string() ?: "Unknown error")

            } catch (e: Exception) {
                val msg = response.errorBody()?.string()
                val errorMessage = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }

                ApiErrorResponseExtensions(errorMessage ?: "Unknown error")
            }
        }

        private const val SUCCESS = 200
    }
}

class ApiSuccessResponseExtensions<T>(val value: T?) : ApiResponseExtensions<T>()
class ApiErrorResponseExtensions<T>(val value: String) : ApiResponseExtensions<T>()