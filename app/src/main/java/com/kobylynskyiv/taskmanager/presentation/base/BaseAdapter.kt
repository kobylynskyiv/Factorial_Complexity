package com.kobylynskyiv.taskmanager.presentation.base

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kobylynskyiv.taskmanager.presentation.utils.ItemDiffCallback
import com.kobylynskyiv.taskmanager.presentation.utils.NoViewException
import com.kobylynskyiv.taskmanager.presentation.utils.PositionNotFoundException


class BaseAdapter<T: AdapterModel>(
    val onClickListener: ((Any?) -> Unit)? = null
) : ListAdapter<T, RecyclerView.ViewHolder>(ItemDiffCallback<T>()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            else -> throw NoViewException()
        }
    }

    override fun getItemViewType(position: Int): Int = (getItem(position) as AdapterModel).layout ?: throw PositionNotFoundException()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            //is AdapterTaskViewHolder ->  holder.bind(getItem(position) as AdapterModel.TaskModel)
        }
    }

}

