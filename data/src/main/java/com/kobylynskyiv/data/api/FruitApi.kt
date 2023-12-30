package com.kobylynskyiv.data.api

import com.kobylynskyiv.core.domain.FruitCore
import retrofit2.Call
import retrofit2.http.GET

fun interface FruitApi {

    @GET("items/random")
    fun getFruits(): Call<FruitCore>
}