package com.example.log_api.domain.model

import java.time.Instant

data class LogEntryDisplay(
    val id: Int = 0,
    val description: String,
    val calories: Double,
    val carbohydrates: Double,
    val protein: Double,
    val loggedAt: Instant?,
    val formattedLoggedAt: String
)
