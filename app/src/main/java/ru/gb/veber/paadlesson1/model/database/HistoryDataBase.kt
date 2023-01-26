package ru.gb.veber.paadlesson1.model.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class HistoryDataBase : RoomDatabase() {
    // Возвращаем DAO
    abstract fun historyDao(): HistoryDao
}