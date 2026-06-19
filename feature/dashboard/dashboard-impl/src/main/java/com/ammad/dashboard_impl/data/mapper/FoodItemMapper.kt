package com.ammad.dashboard_impl.data.mapper

import com.ammad.dashboard_impl.data.remote.dto.FoodItemDto
import com.ammad.dashboard_impl.data.remote.dto.FoodSearchItemDto
import com.ammad.dashboard_impl.domain.model.FoodItem
import com.ammad.dashboard_impl.domain.model.FoodSearchItem

fun FoodItemDto.toDomain(): FoodItem {
    return FoodItem(
        description = description,
        fdcId = fdcId,
        ingredients = ingredients,
        labelNutrients = labelNutrients,
        servingSize = servingSize.toString(),
        servingSizeUnit = servingSizeUnit
    )
}

fun FoodSearchItemDto.toDomain(): FoodSearchItem {
    return FoodSearchItem(
        foods = foods
    )
}

