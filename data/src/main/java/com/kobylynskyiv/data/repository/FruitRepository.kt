package com.kobylynskyiv.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.kobylynskyiv.core.data.FruitDataSource
import com.kobylynskyiv.core.domain.FruitCore
import com.kobylynskyiv.data.api.FruitApi
import com.kobylynskyiv.data.helper.ApiErrorResponseExtensions
import com.kobylynskyiv.data.helper.ApiResponseExtensions
import com.kobylynskyiv.data.helper.ApiSuccessResponseExtensions
import com.kobylynskyiv.data.helper.ApiWrapperHelper
import com.kobylynskyiv.data.helper.NetworkHelper
import com.kobylynskyiv.data.local.dao.FruitDao
import de.musichin.reactivelivedata.merge
import de.musichin.reactivelivedata.observe
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FruitRepository @Inject constructor(
    private val remote: FruitApi,
    private val local: FruitDao
): FruitDataSource {

    val _observable = MutableLiveData<ApiResponseExtensions<*>>()


    override suspend fun getAllFruits() {
    /*    if (!NetworkHelper.isInternetPing() && local.getRowCount() > EMPTY) {
            _observable.postValue(
                ApiDataResult.UserDataSuccess(
                    local.getAllTasksAsFlow()
                )
            return
        }*/

        ApiWrapperHelper.wrapper(remote.getFruits()) {
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