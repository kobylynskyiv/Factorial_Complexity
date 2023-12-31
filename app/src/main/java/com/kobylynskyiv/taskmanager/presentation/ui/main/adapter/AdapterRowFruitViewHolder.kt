package com.kobylynskyiv.taskmanager.presentation.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kobylynskyiv.data.BuildConfig
import com.kobylynskyiv.taskmanager.R
import com.kobylynskyiv.taskmanager.databinding.RowFruitBinding
import com.kobylynskyiv.taskmanager.presentation.base.AdapterModel
import com.kobylynskyiv.taskmanager.presentation.utils.extentions.setTint

class AdapterRowFruitViewHolder(private val binding: RowFruitBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(fruitModel: AdapterModel.FruitModel, onClickListener: ((Any) -> Any?)?) {
        with(fruitModel.fruit) {
            binding.title.text = name.toString()
            binding.root.setTint(color)
            binding.root.setOnClickListener {
                onClickListener?.invoke(this.id.toString())
            }

            Glide.with(binding.root.context)
                .load("${BuildConfig.API_URL}$image")
                .placeholder(R.drawable.alerter_ic_face)
                .into(binding.image)
        }
    }
}