package com.kobylynskyiv.data.model

import androidx.annotation.Keep
import java.util.Date

@Keep
data class Fruit(
    val id: String? = "",
    val name: String? = "",
    val image: String? = "",
    var color: String? = "",
    val data: Date = Date()
){
    init {
        this.color = "#$color"
    }
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
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (image?.hashCode() ?: 0)
        result = 31 * result + (color?.hashCode() ?: 0)
        result = 31 * result + data.hashCode()
        return result
    }


}
