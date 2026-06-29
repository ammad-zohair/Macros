package com.ammad.dashboard_impl.data.remote.dto

data class LabelNutrients(
    val calcium: Calcium? = Calcium(),
    val calories: Calories? = Calories(),
    val carbohydrates: Carbohydrates? = Carbohydrates(),
    val cholesterol: Cholesterol? = Cholesterol(),
    val fat: Fat? = Fat(),
    val fiber: Fiber? = Fiber(),
    val iron: Iron? = Iron(),
    val potassium: Potassium? = Potassium(),
    val protein: Protein? = Protein(),
    val saturatedFat: SaturatedFat? = SaturatedFat(),
    val sodium: Sodium? = Sodium(),
    val sugars: Sugars? = Sugars(),
    val transFat: TransFat? = TransFat()
)