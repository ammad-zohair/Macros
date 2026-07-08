package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.database.converter.DateConverters
import com.example.database.dao.FavoriteDao
import com.example.database.dao.LogDao
import com.example.database.entity.FavoriteEntity
import com.example.database.entity.LogEntity

@Database(
    entities = [FavoriteEntity::class, LogEntity::class],
    version = 1
)
@TypeConverters(DateConverters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun logDao(): LogDao
}