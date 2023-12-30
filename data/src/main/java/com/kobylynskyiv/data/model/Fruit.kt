package com.kobylynskyiv.data.model

import java.util.Date

data class Fruit(
    val id: Int = 0,
    val name: String,
    val image: String,
    val color: String,
    val data: Date
){

    override fun toString(): String {
        return "Fruit(id=$id, name='$name', image='$image', color='$color', data=$data)"
    }
}
