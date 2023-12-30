package com.kobylynskyiv.data.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import java.security.AccessController.getContext

object NetworkHelper {

    fun isInternetPing(): Boolean {
        return try {
            val command = "ping -c 1 google.com"
            Runtime.getRuntime().exec(command).waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }

}