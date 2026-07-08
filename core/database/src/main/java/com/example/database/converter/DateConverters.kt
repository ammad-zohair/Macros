package com.example.database.converter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import kotlin.time.ExperimentalTime
import java.time.Instant

class DateConverters {

    @TypeConverter
    fun fromInstant(instant: Instant?): Long? = instant?.toEpochMilli()

    @TypeConverter
    fun toInstant(value: Long?): Instant? = value?.let{ Instant.ofEpochMilli(it) }
}