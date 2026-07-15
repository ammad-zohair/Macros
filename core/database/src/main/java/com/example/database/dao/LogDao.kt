package com.example.database.dao

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import com.example.database.entity.LogEntity
import com.example.database.model.DailyTotals
import kotlinx.coroutines.flow.Flow
import java.time.Instant

@Dao
interface LogDao {
    @Query("SELECT * FROM log WHERE loggedAt BETWEEN :from AND :to ORDER BY loggedAt DESC")
    fun observeLogs(from: Instant, to: Instant): Flow<List<LogEntity>>

    @Query(
        """
        SELECT 
            COALESCE(SUM(calories), 0.0) AS totalCalories,
            COALESCE(SUM(protein), 0.0) AS totalProtein,
            COALESCE(SUM(carbohydrates), 0.0) AS totalCarbohydrates
        FROM log 
        WHERE loggedAt BETWEEN :from AND :to
        """
    )
    fun observeDailyTotals(from: Instant, to: Instant): Flow<DailyTotals>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(logEntity: LogEntity)

    @Delete
    suspend fun deleteLog(logEntity: LogEntity)
}