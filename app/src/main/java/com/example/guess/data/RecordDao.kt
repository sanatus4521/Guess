package com.example.guess.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.selects.select

@Dao
interface RecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(record: Record)

    @Query("select * from Record")
    suspend fun  getAll() : List<Record>
}