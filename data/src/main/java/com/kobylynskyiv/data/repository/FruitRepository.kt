package com.kobylynskyiv.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kobylynskyiv.core.data.FruitDataSource
import com.kobylynskyiv.core.domain.FruitCore
import com.kobylynskyiv.data.api.FruitApi
import com.kobylynskyiv.data.helper.ApiErrorResponseExtensions
import com.kobylynskyiv.data.helper.ApiSuccessResponseExtensions
import com.kobylynskyiv.data.helper.ApiWrapperHelper
import com.kobylynskyiv.data.helper.NetworkHelper
import com.kobylynskyiv.data.local.dao.FruitDao
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FruitRepository @Inject constructor(
    private val remote: FruitApi,
    private val local: FruitDao
): FruitDataSource {

    internal val _observable = MutableLiveData<Any>()

    override suspend fun getAllFruits() {
        if (!NetworkHelper.isInternetPing() && local.getRowCount() > EMPTY) {
            _observable.postValue(local.getAllTasksAsFlow())
            return
        }

        ApiWrapperHelper.wrapper(remote.getFruits()) {
            when (it) {
                is ApiSuccessResponseExtensions -> _observable.postValue(it.value)
                is ApiErrorResponseExtensions -> _observable.postValue(it.value)
            }
        }
    }

    companion object {
        const val EMPTY = 0
    }
}