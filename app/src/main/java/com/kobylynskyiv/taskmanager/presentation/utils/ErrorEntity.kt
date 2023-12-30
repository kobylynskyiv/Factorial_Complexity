package com.kobylynskyiv.taskmanager.presentation.utils

enum class ErrorEntity {
    TITLE,
    DESCRIPTION;

    override fun toString(): String =
        when (this) {
            TITLE -> "Title"
            DESCRIPTION -> "Description"
        }
}