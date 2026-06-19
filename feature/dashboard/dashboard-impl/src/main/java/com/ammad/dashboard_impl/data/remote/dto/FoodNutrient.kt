package com.ammad.dashboard_impl.data.remote.dto

data class FoodNutrient(
    val amount: Double,
    val foodNutrientDerivation: FoodNutrientDerivation,
    val id: Int,
    val nutrient: Nutrient,
    val type: String
)