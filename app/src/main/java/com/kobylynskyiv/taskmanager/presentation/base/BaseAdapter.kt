package com.kobylynskyiv.taskmanager.presentation.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kobylynskyiv.taskmanager.R
import com.kobylynskyiv.taskmanager.databinding.RowFruitBinding
import com.kobylynskyiv.taskmanager.presentation.ui.main.adapter.AdapterRowFruitViewHolder
import com.kobylynskyiv.taskmanager.presentation.utils.ItemDiffCallback
import com.kobylynskyiv.taskmanager.presentation.utils.NoViewException
import com.kobylynskyiv.taskmanager.presentation.utils.PositionNotFoundException


class BaseAdapter<T: AdapterModel> : ListAdapter<T, RecyclerView.ViewHolder>(ItemDiffCallback<T>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.row_fruit -> AdapterRowFruitViewHolder(RowFruitBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw NoViewException()
        }
    }

    override fun getItemViewType(position: Int): Int = (getItem(position) as AdapterModel).layout ?: throw PositionNotFoundException()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AdapterRowFruitViewHolder -> holder.bind(getItem(position) as AdapterModel.FruitModel)
        }
    }

}

