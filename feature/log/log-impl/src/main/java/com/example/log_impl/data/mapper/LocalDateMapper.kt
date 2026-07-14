package com.example.log_impl.data.mapper

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toDayLabel(today: LocalDate = LocalDate.now()): String? = when (this) {
    today -> "Today"
    today.minusDays(1) -> "Yesterday"
    today.plusDays(1) -> "Tomorrow"
    else -> null
}

fun LocalDate.toDateText(): String =
    format(DateTimeFormatter.ofPattern("EEEE, MMM d"))