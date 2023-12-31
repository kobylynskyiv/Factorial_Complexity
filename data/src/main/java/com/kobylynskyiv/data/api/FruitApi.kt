package com.kobylynskyiv.data.api

import com.kobylynskyiv.core.domain.ResponseFruitCore
import retrofit2.Call
import retrofit2.http.GET

fun interface FruitApi {

    @GET("/items/random")
    fun getFruits(): Call<ResponseFruitCore>
}