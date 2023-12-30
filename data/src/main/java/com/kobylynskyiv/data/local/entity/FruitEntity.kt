package com.kobylynskyiv.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

@Entity(tableName = "tasks")
@TypeConverters(com.kobylynskyiv.taskmanager.data.local.utils.TypeConverters::class)
data class FruitEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "color") val color: String,
    @ColumnInfo(name = "data") val data: Date,
)
