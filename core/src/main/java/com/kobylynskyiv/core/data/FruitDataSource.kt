package com.kobylynskyiv.core.data

interface FruitDataSource {
    suspend fun getAllFruits()
    suspend fun queryByIdFruit(id: String)
}