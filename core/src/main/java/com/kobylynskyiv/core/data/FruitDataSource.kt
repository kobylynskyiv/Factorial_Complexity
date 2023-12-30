package com.kobylynskyiv.core.data

fun interface FruitDataSource {
    suspend fun getAllFruits()
}