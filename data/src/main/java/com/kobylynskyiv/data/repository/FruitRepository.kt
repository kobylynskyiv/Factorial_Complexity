package com.kobylynskyiv.data.repository

import com.kobylynskyiv.core.data.FruitDataSource
import com.kobylynskyiv.data.api.FruitApi
import com.kobylynskyiv.data.helper.ApiErrorResponseExtensions
import com.kobylynskyiv.data.helper.ApiResponseExtensions
import com.kobylynskyiv.data.helper.ApiSuccessResponseExtensions
import com.kobylynskyiv.data.helper.ApiWrapperHelper
import com.kobylynskyiv.data.helper.NetworkHelper
import com.kobylynskyiv.data.local.dao.FruitDao
import com.kobylynskyiv.data.utils.SingleLiveData
import javax.inject.Inject

class FruitRepository @Inject constructor(
    private val remote: FruitApi,
    private val local: FruitDao
): FruitDataSource {

    val _observable = SingleLiveData<ApiResponseExtensions<*>>()


    override suspend fun getAllFruits() {
        if (!NetworkHelper.isInternetPing() && local.getRowCount() > EMPTY) {
            // local database
            return
        }

        ApiWrapperHelper.wrapper(remote.getFruits()) {
            when (it) {
                is ApiSuccessResponseExtensions -> _observable.postValue(it)
                is ApiErrorResponseExtensions -> _observable.postValue(it)
            }
        }
    }

    override suspend fun queryByIdFruit(id: String) {
        if(id.isEmpty()) return

        ApiWrapperHelper.wrapper(remote.getFruitById(id)) {
            when (it) {
                is ApiSuccessResponseExtensions -> _observable.postValue(it)
                is ApiErrorResponseExtensions -> _observable.postValue(it)
            }
        }
    }

    companion object {
        const val EMPTY = 0
    }
}