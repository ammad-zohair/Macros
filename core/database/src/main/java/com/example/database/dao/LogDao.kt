package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.LogEntity
import kotlinx.coroutines.flow.Flow
import java.time.Instant

@Dao
interface LogDao {
    @Query("SELECT * FROM log WHERE loggedAt BETWEEN :from AND :to")
    fun observeLogs(from: Instant, to: Instant): Flow<List<LogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(logEntity: LogEntity)
}