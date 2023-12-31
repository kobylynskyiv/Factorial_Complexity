package com.kobylynskyiv.taskmanager.presentation.base

import androidx.annotation.LayoutRes
import com.kobylynskyiv.data.model.Fruit
import com.kobylynskyiv.taskmanager.R

sealed class AdapterModel(val id: Int, @LayoutRes val layout: Int?){

    class FruitModel(val fruit: Fruit) : AdapterModel(0, R.layout.row_fruit)
}