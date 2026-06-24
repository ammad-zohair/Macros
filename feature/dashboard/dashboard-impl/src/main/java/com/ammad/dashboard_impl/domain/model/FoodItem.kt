package com.ammad.dashboard_impl.domain.model

import com.ammad.dashboard_impl.data.remote.dto.Calcium
import com.ammad.dashboard_impl.data.remote.dto.Calories
import com.ammad.dashboard_impl.data.remote.dto.Carbohydrates
import com.ammad.dashboard_impl.data.remote.dto.Cholesterol
import com.ammad.dashboard_impl.data.remote.dto.Fat
import com.ammad.dashboard_impl.data.remote.dto.Fiber
import com.ammad.dashboard_impl.data.remote.dto.Iron
import com.ammad.dashboard_impl.data.remote.dto.LabelNutrients
import com.ammad.dashboard_impl.data.remote.dto.Potassium
import com.ammad.dashboard_impl.data.remote.dto.Protein
import com.ammad.dashboard_impl.data.remote.dto.SaturatedFat
import com.ammad.dashboard_impl.data.remote.dto.Sodium
import com.ammad.dashboard_impl.data.remote.dto.Sugars
import com.ammad.dashboard_impl.data.remote.dto.TransFat

data class FoodItem(
    val description: String = "",
    val fdcId: Int = 0,
    val ingredients: String = "",
    val servingSize: String = "",
    val servingSizeUnit: String = "",
    val labelNutrients: LabelNutrients = LabelNutrients(
        calcium = Calcium(0.0),
        calories = Calories(0.0),
        carbohydrates = Carbohydrates(0.0),
        cholesterol = Cholesterol(0.0),
        fat = Fat(0.0),
        fiber = Fiber(0.0),
        iron = Iron(0.0),
        potassium = Potassium(0),
        protein = Protein(0.0),
        saturatedFat = SaturatedFat(0.0),
        sodium = Sodium(0.0),
        sugars = Sugars(0.0),
        transFat = TransFat(0.0)
    )
)
