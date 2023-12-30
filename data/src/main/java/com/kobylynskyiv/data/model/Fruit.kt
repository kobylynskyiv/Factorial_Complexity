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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Fruit

        if (id != other.id) return false
        if (name != other.name) return false
        if (image != other.image) return false
        if (color != other.color) return false
        return data == other.data
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + data.hashCode()
        return result
    }


}
