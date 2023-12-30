package com.kobylynskyiv.taskmanager.presentation.base

import androidx.annotation.LayoutRes
import com.kobylynskyiv.data.local.entity.FruitEntity
import com.kobylynskyiv.taskmanager.R

sealed class AdapterModel(val id: Int, @LayoutRes val layout: Int?){

    class FruitModel(val fruit: FruitEntity) : AdapterModel(0, R.layout.row_task)
}