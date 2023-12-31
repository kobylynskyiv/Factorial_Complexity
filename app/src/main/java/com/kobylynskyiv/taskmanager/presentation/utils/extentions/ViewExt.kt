package com.kobylynskyiv.taskmanager.presentation.utils.extentions

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View

fun View.visibleOrGone(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
fun View.visibleOrInvisible(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

fun View.setTint(color: String?){
    if(color == null) return
    this.backgroundTintList = ColorStateList.valueOf(Color.parseColor(color));
}