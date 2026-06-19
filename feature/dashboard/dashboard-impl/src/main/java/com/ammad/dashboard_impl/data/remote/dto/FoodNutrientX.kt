package com.ammad.dashboard_impl.data.remote.dto

data class FoodNutrientX(
    val dataPoints: Int,
    val derivationCode: String,
    val derivationDescription: String,
    val derivationId: Int,
    val foodNutrientId: Int,
    val foodNutrientSourceCode: String,
    val foodNutrientSourceDescription: String,
    val foodNutrientSourceId: Int,
    val indentLevel: Int,
    val max: Double,
    val median: Double,
    val min: Double,
    val nutrientId: Int,
    val nutrientName: String,
    val nutrientNumber: String,
    val percentDailyValue: Int,
    val rank: Int,
    val unitName: String,
    val value: Double
)