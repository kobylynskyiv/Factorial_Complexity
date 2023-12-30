package com.kobylynskyiv.taskmanager.presentation.utils.alert

import android.app.Activity
import androidx.annotation.ColorRes
import com.tapadoo.alerter.R
import javax.inject.Singleton
import com.tapadoo.alerter.Alerter as AlerterImpl

class Alerter(
    private val activity: Activity
) {

    fun showWarning(
        title: String,
        message: String,
    ) = showAlert(
        title = title,
        message = message,
        backgroundColor = com.kobylynskyiv.taskmanager.R.color.orange
    )

    fun showError(
        title: String,
        message: String
    ) = showAlert(
        title = title,
        message = message,
        backgroundColor = R.color.alert_default_error_background
    )

    fun showSuccess(
        title: String,
        message: String,
    ) = showAlert(
        title = title,
        message = message,
        backgroundColor = R.color.alerter_default_success_background
    )

    fun show(
        title: String,
        message: String,
    ) = showAlert(
        title = title,
        message = message,
        backgroundColor = R.color.notification_icon_bg_color
    )

    private fun showAlert(
        title: String,
        message: String,
        @ColorRes backgroundColor: Int
    ) = AlerterImpl.create(activity)
        .setTitle(title)
        .setText(message)
        .setBackgroundColorRes(backgroundColor)
        .show()
}