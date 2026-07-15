package com.example.log_impl.data.mapper

import com.example.database.entity.LogEntity
import com.example.log_api.domain.model.Log
import com.example.log_api.domain.model.LogEntryDisplay
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

fun LogEntity.toDomain(): Log {
    return Log(
        id = id,
        description = description,
        calories = calories,
        carbohydrates = carbohydrates,
        protein = protein,
        loggedAt = loggedAt
    )
}

fun Log.toEntity(): LogEntity {
    return LogEntity(
        id = id,
        description = description,
        calories = calories,
        carbohydrates = carbohydrates,
        protein = protein,
        loggedAt = loggedAt
    )
}

fun Log.toDisplay(): LogEntryDisplay {
    val formattedTime = loggedAt
        ?.atZone(ZoneId.systemDefault())
        ?.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))
        ?: "Unknown time"
    return LogEntryDisplay(
        id = id,
        description = description,
        calories = calories,
        carbohydrates = carbohydrates,
        protein = protein,
        loggedAt = loggedAt,
        formattedLoggedAt = formattedTime
    )
}

fun LogEntryDisplay.toEntity(): LogEntity {
    return LogEntity(
        id = id,
        description = description,
        calories = calories,
        carbohydrates = carbohydrates,
        protein = protein,
        loggedAt = loggedAt
    )
}