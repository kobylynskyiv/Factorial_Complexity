package com.kobylynskyiv.taskmanager.presentation.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.kobylynskyiv.data.model.Fruit
import com.kobylynskyiv.data.usecase.GetFruitsUseCases
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
            is Fruit -> UI(fruit = it)
            else -> UI(error = it as String)
        }
    }

    val observer: LiveData<UI> get() = _observer

    fun load() = viewModelScope.launch(IO) {
        fruitsUseCases.invoke()
    }

    class UI(val fruit: Fruit? = null, val error: String? = null)
}