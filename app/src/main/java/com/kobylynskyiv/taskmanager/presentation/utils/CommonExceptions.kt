package com.kobylynskyiv.taskmanager.presentation.utils

import com.kobylynskyiv.taskmanager.R
import com.kobylynskyiv.taskmanager.presentation.ui.App
import java.io.IOException

class NoViewException : IOException() {
    override fun getLocalizedMessage(): String {
        return App.getContext().getString(R.string.error_no_found_view)
    }
}

class PositionNotFoundException: IOException(){
    override fun getLocalizedMessage(): String {
        return App.getContext().getString(R.string.error_no_found_position)
    }
}

class NoFoundAppContext(): Error() {
    override fun getLocalizedMessage(): String {
        return App.getContext().getString(R.string.error_no_found_context)
    }

}