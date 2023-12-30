package com.kobylynskyiv.taskmanager.presentation.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.kobylynskyiv.data.local.entity.FruitEntity
import com.kobylynskyiv.data.usecase.GetFruitsUseCases
import com.kobylynskyiv.taskmanager.presentation.base.AdapterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fruitsUseCases: GetFruitsUseCases
): ViewModel(){

    private val _observer = fruitsUseCases.value
    val observer: LiveData<Any> get() = _observer

    fun load() = viewModelScope.launch(IO) {
        fruitsUseCases.invoke()
    }
}