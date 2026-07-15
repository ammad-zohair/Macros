package com.example.log_impl.data.repository

import com.example.database.dao.LogDao
import com.example.database.model.DailyTotals
import com.example.log_api.domain.model.Log
import com.example.log_api.domain.model.LogEntryDisplay
import com.example.log_api.domain.repository.LogRepository
import com.example.log_impl.data.mapper.toDomain
import com.example.log_impl.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class LogRepositoryImpl(
    private val logDao: LogDao
) : LogRepository {
    override fun observeLogs(date: LocalDate): Flow<List<Log>> {
        val (start, end) = getDayRange(date)
        return logDao.observeLogs(start, end).map { logsList ->
            logsList.map { it.toDomain() }
        }

    }

    override fun observeDailyTotals(date: LocalDate): Flow<DailyTotals> {
        val (start, end) = getDayRange(date)
        return logDao.observeDailyTotals(start, end)
    }

    override suspend fun insertLog(log: Log) {
        logDao.insertLog(log.toEntity())
    }

    override suspend fun deleteLog(log: LogEntryDisplay) {
        logDao.deleteLog(log.toEntity())
    }

    fun getDayRange(date: LocalDate, zoneId: ZoneId = ZoneId.systemDefault()): Pair<Instant, Instant> {
        val startOfDay = date.atStartOfDay(zoneId).toInstant()
        val endOfDay = date.plusDays(1).atStartOfDay(zoneId).toInstant()
        return startOfDay to endOfDay
    }
}