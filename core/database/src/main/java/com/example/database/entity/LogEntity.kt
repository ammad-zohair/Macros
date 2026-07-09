package com.example.database.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey
import java.time.Instant

@Entity(tableName = "log")
data class LogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String,
    val calories: Double = 0.0,
    val carbohydrates: Double = 0.0,
    val protein: Double = 0.0,
    val loggedAt: Instant? = null
)
