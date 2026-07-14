package com.example.log_impl.data.mapper

import com.example.database.entity.LogEntity
import com.example.database.model.DailyTotals
import com.example.log_api.domain.model.Log
import com.example.log_api.domain.model.MacroDisplay
import java.util.Locale

fun LogEntity.toDomain(): Log {
    return Log(
        description = description,
        calories = calories,
        carbohydrates = carbohydrates,
        protein = protein,
        loggedAt = loggedAt
    )
}

fun Log.toEntity(): LogEntity {
    return LogEntity(
        description = description,
        calories = calories,
        carbohydrates = carbohydrates,
        protein = protein,
        loggedAt = loggedAt
    )
}