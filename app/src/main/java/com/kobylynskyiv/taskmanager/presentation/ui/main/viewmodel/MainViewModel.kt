package com.kobylynskyiv.taskmanager.presentation.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.kobylynskyiv.core.domain.FruitCore
import com.kobylynskyiv.data.model.Fruit
import com.kobylynskyiv.data.usecase.GetFruitsUseCases
import com.kobylynskyiv.taskmanager.presentation.base.AdapterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fruitsUseCases: GetFruitsUseCases
): ViewModel(){

    private val _observer = fruitsUseCases.value.map {
        return@map when(it){
            is List<*> -> UI(fruits = it.map { fruitModel -> AdapterModel.FruitModel(fruitModel as Fruit) })
            else -> UI(error = it as String)
        }
    }

    val observer: LiveData<UI> get() = _observer

    fun fetch() = viewModelScope.launch(IO) {
        fruitsUseCases.invoke()
    }

    class UI(val fruits: List<AdapterModel.FruitModel>? = null, val error: String? = null)
}