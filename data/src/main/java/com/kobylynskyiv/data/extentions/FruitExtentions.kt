package com.kobylynskyiv.data.extentions

import com.kobylynskyiv.core.domain.FruitCore
import com.kobylynskyiv.data.model.Fruit
import java.util.Date

/**
    We convert pojo information from the main (core) module,
    where the changes(all system) are common to all and independent
 */
fun FruitCore.toFruitData(): Fruit {
    return Fruit(
        id = this.id,
        name = this.name,
        image = this.image,
        color = this.color
    )
}