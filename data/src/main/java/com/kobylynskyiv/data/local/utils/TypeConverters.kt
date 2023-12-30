package com.kobylynskyiv.taskmanager.data.local.utils

import androidx.room.TypeConverter
import java.util.*

class TypeConverters {

    @TypeConverter
    fun contactToDateInfo(dateInfo: Date): Long {
        return dateInfo.time
    }
    @TypeConverter
    fun dateInfoToDate(value: Long): Date {
        return Date(value)
    }

}