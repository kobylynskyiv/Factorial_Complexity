package com.kobylynskyiv.taskmanager.presentation.utils.extentions

fun String.replaceAllVariable(tokens: Map<String, String?>): String {
    return tokens.entries.fold(this) { string, token ->
        string.replace(token.key, token.value.toString())
    }
}