package com.kobylynskyiv.data.helper

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiWrapperHelper {
    fun <T> wrapper(call: Call<T>, resultWrapper: (ApiResponseExtensions<T>) -> Any?) {
        if (NetworkHelper.isInternetPing()) {
            call.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    resultWrapper.invoke(ApiResponseExtensions(response))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    resultWrapper.invoke(ApiErrorResponseExtensions(t.message.toString()))
                }
            })
        } else {
            resultWrapper.invoke(ApiErrorResponseExtensions("Check internet connection!"))
        }
    }


}