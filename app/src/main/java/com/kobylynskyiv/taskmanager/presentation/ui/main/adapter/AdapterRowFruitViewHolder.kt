package com.kobylynskyiv.taskmanager.presentation.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kobylynskyiv.data.BuildConfig
import com.kobylynskyiv.taskmanager.databinding.RowFruitBinding
import com.kobylynskyiv.taskmanager.presentation.base.AdapterModel
import com.kobylynskyiv.taskmanager.presentation.utils.extentions.setTint

class AdapterRowFruitViewHolder(private val binding: RowFruitBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(fruitModel: AdapterModel.FruitModel) {
        with(fruitModel.fruit) {
            binding.title.text = name.toString()
            binding.root.setTint(color)

            Glide.with(binding.root.context)
                .load("${BuildConfig.API_URL}$image")
                .into(binding.image)
        }
    }
}