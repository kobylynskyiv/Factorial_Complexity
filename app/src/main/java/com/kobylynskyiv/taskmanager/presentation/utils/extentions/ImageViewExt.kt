package com.kobylynskyiv.taskmanager.presentation.utils.extentions

import android.graphics.Color
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat


fun ImageView.setTint(color: String){
    if(color == null) return
    this.drawable.setTint(Color.parseColor("#$color"))
}