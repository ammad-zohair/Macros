package com.example.favorite_impl.data.mapper

import com.example.database.entity.FavoriteEntity
import com.example.favorite_api.domain.model.Favorite

fun FavoriteEntity.toDomain(): Favorite {
    return Favorite(
        description = description,
        servingSize = servingSize,
        servingSizeUnit = servingSizeUnit,
        calories = calories,
        carbohydrates = carbohydrates,
        protein = protein,
        fat = fat,
        sugar = sugar,
        sodium = sodium
    )
}

fun Favorite.toEntity(): FavoriteEntity {
    return FavoriteEntity(
        description = description,
        servingSize = servingSize,
        servingSizeUnit = servingSizeUnit,
        calories = calories,
        carbohydrates = carbohydrates,
        protein = protein,
        fat = fat,
        sugar = sugar,
        sodium = sodium
    )
}