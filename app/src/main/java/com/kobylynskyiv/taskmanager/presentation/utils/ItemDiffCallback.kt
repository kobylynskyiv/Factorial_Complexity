package com.kobylynskyiv.taskmanager.presentation.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.kobylynskyiv.taskmanager.presentation.base.AdapterModel

class ItemDiffCallback<T : AdapterModel> : DiffUtil.ItemCallback<T>() {

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

}
