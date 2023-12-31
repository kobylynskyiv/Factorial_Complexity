package com.kobylynskyiv.taskmanager.presentation.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View


// when access single thread
fun <T> lazyNonSafetyMode(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

// access more than single thread
fun <T> lazySynchronizedMode(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.SYNCHRONIZED, initializer)
