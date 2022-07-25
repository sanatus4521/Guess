package com.example.guess.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Record::class), version = 1)
abstract class GameDatabase : RoomDatabase() {
    abstract fun recordDao() : RecordDao
}