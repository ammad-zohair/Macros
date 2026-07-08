package com.example.favorite_api.domain.repository

import com.example.favorite_api.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun observeFavorites(): Flow<List<Favorite>>
    suspend fun addFavorite(favorite: Favorite)
    suspend fun getFavoriteCount(): Int
}