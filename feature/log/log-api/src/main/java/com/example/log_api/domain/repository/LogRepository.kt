package com.example.log_api.domain.repository

import com.example.database.model.DailyTotals
import com.example.log_api.domain.model.Log
import com.example.log_api.domain.model.LogEntryDisplay
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface LogRepository {
    fun observeLogs(date: LocalDate): Flow<List<Log>>
    fun observeDailyTotals(date: LocalDate): Flow<DailyTotals>
    suspend fun insertLog(log: Log)
    suspend fun deleteLog(log: LogEntryDisplay)
}