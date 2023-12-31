package com.kobylynskyiv.core.domain

import androidx.annotation.Keep

data class ResponseFruitCore(
    val title: String,
    val items: List<FruitCore>
)


data class FruitCore(
    val id: String,
    val name: String,
    val image: String,
    val color: String
)