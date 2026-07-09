package com.example.database.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String,
    val servingSize: String = "",
    val servingSizeUnit: String = "",
    val calories: Double = 0.0,
    val carbohydrates: Double = 0.0,
    val protein: Double = 0.0,
    val fat: Double = 0.0,
    val sugar: Double = 0.0,
    val sodium: Double = 0.0
)
