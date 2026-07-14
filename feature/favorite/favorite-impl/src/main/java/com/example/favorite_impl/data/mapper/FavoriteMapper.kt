package com.example.favorite_impl.data.mapper

import com.example.database.entity.FavoriteEntity
import com.example.favorite_api.domain.model.Favorite
import com.example.log_api.domain.model.Log
import java.time.Instant

fun FavoriteEntity.toDomain(): Favorite {
    return Favorite(
        id = id,
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
        id = id,
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

fun Favorite.toLog(): Log {
    return Log(
        description = description,
        calories = calories,
        carbohydrates = carbohydrates,
        protein = protein,
        loggedAt = Instant.now()
    )
}