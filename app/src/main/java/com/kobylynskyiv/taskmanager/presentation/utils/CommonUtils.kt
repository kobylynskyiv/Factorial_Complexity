package com.kobylynskyiv.taskmanager.presentation.utils

import android.view.View
import android.widget.DatePicker
import java.util.*


// when access single thread
fun <T> lazyNonSafetyMode(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

// access more than single thread
fun <T> lazySynchronizedMode(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.SYNCHRONIZED, initializer)


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