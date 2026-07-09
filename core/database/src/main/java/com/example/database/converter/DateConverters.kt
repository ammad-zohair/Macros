package com.example.database.converter

import androidx.room3.TypeConverter
import java.time.Instant

class DateConverters {

    @TypeConverter
    fun fromInstant(instant: Instant?): Long? = instant?.toEpochMilli()

    @TypeConverter
    fun toInstant(value: Long?): Instant? = value?.let{ Instant.ofEpochMilli(it) }
}