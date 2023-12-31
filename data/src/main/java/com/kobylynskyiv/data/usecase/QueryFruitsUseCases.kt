package com.kobylynskyiv.data.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.kobylynskyiv.core.domain.ResponseFruitDetailCore
import com.kobylynskyiv.data.helper.ApiErrorResponseExtensions
import com.kobylynskyiv.data.helper.ApiSuccessResponseExtensions
import com.kobylynskyiv.data.repository.FruitRepository
import javax.inject.Inject

class QueryFruitsUseCases @Inject constructor(
    private val mFruitRepository: FruitRepository
) {

    private var _value = mFruitRepository._observable.map {
        return@map when (it) {
            is ApiSuccessResponseExtensions -> it.value as ResponseFruitDetailCore
            is ApiErrorResponseExtensions -> it.value
            null -> return@map
        }
    }

    val value: LiveData<Any> = _value

    suspend operator fun invoke(id: String) = mFruitRepository.queryByIdFruit(id)
}