package com.kobylynskyiv.taskmanager.presentation.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.kobylynskyiv.core.domain.ResponseFruitCore
import com.kobylynskyiv.data.di.UtilsProvides
import com.kobylynskyiv.data.extentions.toFruitData
import com.kobylynskyiv.data.usecase.GetFruitsUseCases
import com.kobylynskyiv.taskmanager.presentation.base.AdapterModel
import com.kobylynskyiv.taskmanager.presentation.entity.UIEvent
import com.kobylynskyiv.taskmanager.presentation.entity.UIStatus
import com.kobylynskyiv.taskmanager.presentation.utils.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fruitsUseCases: GetFruitsUseCases,
    @UtilsProvides.IoDispatcher private val default: CoroutineDispatcher = Dispatchers.IO
): ViewModel(){

    val status = SingleLiveData<UIStatus?>()
    val observer = fruitsUseCases.value.map {
        return@map when(it){
            is ResponseFruitCore -> {
                status.postValue(UIStatus.COMPLETE)
                UIEvent(title = it.title, data = it.items.map { fruitModel -> AdapterModel.FruitModel(
                    fruitModel.toFruitData()
                )})
            }
            else -> {
                status.postValue(UIStatus.ERROR)
                UIEvent(error = it as String)
            }
        }
    }

    fun fetch() = viewModelScope.launch(default) {
        status.postValue(UIStatus.LOADING)
        fruitsUseCases.invoke()
    }

    public override fun onCleared() {
        status.postValue(null)
        super.onCleared()
    }


}