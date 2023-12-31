package com.kobylynskyiv.data.api

import com.kobylynskyiv.core.domain.ResponseFruitCore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FruitApi {

    @GET("/items/random")
    fun getFruits(): Call<ResponseFruitCore>

    @GET("/texts/{itemId}")
    fun getFruitById(@Query("itemId") itemId: String)
}