package com.example.log_api.domain.model

import java.time.Instant

data class Log(
    val id: Int = 0,
    val description: String,
    val calories: Double = 0.0,
    val carbohydrates: Double = 0.0,
    val protein: Double = 0.0,
    val loggedAt: Instant? = null
)
