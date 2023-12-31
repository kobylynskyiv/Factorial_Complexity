package com.kobylynskyiv.core.domain

data class ResponseFruitCore(
    val title: String,
    val items: List<FruitCore>
)
data class ResponseFruitDetailCore(
    val id: String,
    val text: String
)

data class FruitCore(
    val id: String,
    val name: String,
    val image: String,
    val color: String
)



