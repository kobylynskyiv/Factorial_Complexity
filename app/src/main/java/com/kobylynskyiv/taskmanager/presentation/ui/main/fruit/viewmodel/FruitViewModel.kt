package com.kobylynskyiv.taskmanager.presentation.ui.main.fruit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.kobylynskyiv.core.domain.ResponseFruitDetailCore
import com.kobylynskyiv.data.di.UtilsProvides
import com.kobylynskyiv.data.usecase.QueryFruitsUseCases
import com.kobylynskyiv.taskmanager.presentation.entity.UIEvent
import com.kobylynskyiv.taskmanager.presentation.entity.UIStatus
import com.kobylynskyiv.taskmanager.presentation.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitViewModel @Inject constructor(
    private val fruitsUseCases: QueryFruitsUseCases,
    @UtilsProvides.IoDispatcher private val default: CoroutineDispatcher = Dispatchers.IO
): ViewModel(){

    val status = MutableLiveData<UIStatus?>()

    private val _observer = fruitsUseCases.value.map {
        return@map when(it){
            is ResponseFruitDetailCore -> {
                status.postValue(UIStatus.COMPLETE)
                UIEvent(data = it.text)
            }
            else -> {
                status.postValue(UIStatus.ERROR)
                UIEvent(error = it as String)
            }
        }
    }

    val observer: LiveData<UIEvent> get() = _observer

    fun loadFruit(id: String) = viewModelScope.launch(default) {
        status.postValue(UIStatus.LOADING)
        fruitsUseCases.invoke(id)
    }

    override fun onCleared() {
        status.postValue(null)
        super.onCleared()
    }
}