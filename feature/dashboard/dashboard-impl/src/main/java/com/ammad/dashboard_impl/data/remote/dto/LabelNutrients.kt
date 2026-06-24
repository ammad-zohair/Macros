package com.ammad.dashboard_impl.data.remote.dto

data class LabelNutrients(
    val calcium: Calcium,
    val calories: Calories? = Calories(),
    val carbohydrates: Carbohydrates? = Carbohydrates(),
    val cholesterol: Cholesterol,
    val fat: Fat? = Fat(),
    val fiber: Fiber? = Fiber(),
    val iron: Iron,
    val potassium: Potassium,
    val protein: Protein? = Protein(),
    val saturatedFat: SaturatedFat,
    val sodium: Sodium? = Sodium(),
    val sugars: Sugars? = Sugars(),
    val transFat: TransFat
)