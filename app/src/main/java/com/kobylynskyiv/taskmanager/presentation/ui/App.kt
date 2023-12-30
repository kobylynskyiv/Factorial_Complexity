package com.kobylynskyiv.taskmanager.presentation.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.StrictMode
import com.kobylynskyiv.taskmanager.BuildConfig
import com.kobylynskyiv.taskmanager.presentation.utils.NoFoundAppContext
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App: Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        debugOnAndPolicy()
    }

    private fun debugOnAndPolicy(){
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Timber.d("App - DEBUG Mode on", "Test")
        } else {
            Timber.uprootAll()
        }

    }

    companion object {

        private var instance: App? = null
        fun getContext(): Context {
            return (instance?.baseContext ?: instance?.applicationContext) ?: throw NoFoundAppContext()
        }
    }
}