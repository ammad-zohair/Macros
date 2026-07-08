package com.ammad.dashboard_impl.data.mapper

import com.ammad.dashboard_impl.data.remote.dto.FoodItemDto
import com.ammad.dashboard_impl.data.remote.dto.FoodSearchItemDto
import com.ammad.dashboard_impl.data.remote.dto.LabelNutrients
import com.ammad.dashboard_impl.domain.model.FoodItem
import com.ammad.dashboard_impl.domain.model.FoodSearchItem
import com.example.favorite_api.domain.model.Favorite

fun FoodItemDto.toDomain(): FoodItem {
    return FoodItem(
        description = description,
        fdcId = fdcId,
        ingredients = ingredients ?: "",
        labelNutrients = labelNutrients ?: LabelNutrients(),
        servingSize = servingSize.toString(),
        servingSizeUnit = servingSizeUnit ?: "unit"
    )
}

fun FoodSearchItemDto.toDomain(): FoodSearchItem {
    return FoodSearchItem(
        foods = foods
    )
}

fun FoodItem.toFavorite(): Favorite {
    return Favorite(
        description = description,
        calories = labelNutrients.calories?.value ?: 0.0,
        servingSize = servingSize,
        servingSizeUnit = servingSizeUnit,
        carbohydrates = labelNutrients.carbohydrates?.value ?: 0.0,
        protein = labelNutrients.protein?.value ?: 0.0,
        fat = labelNutrients.fat?.value ?: 0.0,
        sugar = labelNutrients.sugars?.value ?: 0.0,
        sodium = labelNutrients.sodium?.value ?: 0.0
    )
}
