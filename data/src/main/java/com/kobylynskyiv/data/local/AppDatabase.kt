package com.kobylynskyiv.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kobylynskyiv.data.local.dao.FruitDao
import com.kobylynskyiv.data.local.entity.FruitEntity

@Database(
    version = 1,
    entities = [
        FruitEntity::class
    ],
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase()  {

    abstract fun getFruitDao(): FruitDao

    companion object {
        fun getInstance(context: Context): AppDatabase =
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database"
                )
                    .allowMainThreadQueries()
                    .build()
    }
}