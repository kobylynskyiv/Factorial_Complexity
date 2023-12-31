package com.kobylynskyiv.taskmanager.presentation.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.kobylynskyiv.core.domain.ResponseFruitCore
import com.kobylynskyiv.data.extentions.toFruitData
import com.kobylynskyiv.data.model.Fruit
import com.kobylynskyiv.data.usecase.GetFruitsUseCases
import com.kobylynskyiv.taskmanager.presentation.base.AdapterModel
import com.kobylynskyiv.taskmanager.presentation.entity.UIEvent
import com.kobylynskyiv.taskmanager.presentation.entity.UIStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fruitsUseCases: GetFruitsUseCases
): ViewModel(){

    val status = MutableLiveData<UIStatus>()
    private val _observer = fruitsUseCases.value.map {
        return@map when(it){
            is ResponseFruitCore -> {
                status.postValue(UIStatus.COMPLETE)
                UIEvent(title = it.title, fruits = it.items.map { fruitModel -> AdapterModel.FruitModel(
                    fruitModel.toFruitData()
                )})
            }
            else -> {
                status.postValue(UIStatus.ERROR)
                UIEvent(error = it as String)
            }
        }
    }

    val observer: LiveData<UIEvent> get() = _observer

    fun fetch() = viewModelScope.launch(IO) {
        status.postValue(UIStatus.LOADING)
        fruitsUseCases.invoke()
    }


}