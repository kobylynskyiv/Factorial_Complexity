package com.kobylynskyiv.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.kobylynskyiv.data.local.entity.FruitEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FruitDao {

    @Query("SELECT * from tasks")
    fun getAllTasksAsFlow(): Flow<List<FruitEntity>>

    @Query("SELECT COUNT(*) FROM tasks")
    fun getRowCount(): Int

    @Query("SELECT * from tasks WHERE color = :color")
    fun getAllFruitByColor(color: String): Flow<List<FruitEntity>>

}