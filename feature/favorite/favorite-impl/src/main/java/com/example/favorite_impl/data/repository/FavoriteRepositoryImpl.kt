package com.example.favorite_impl.data.repository

import com.example.database.dao.FavoriteDao
import com.example.favorite_api.domain.model.Favorite
import com.example.favorite_api.domain.repository.FavoriteRepository
import com.example.favorite_impl.data.mapper.toDomain
import com.example.favorite_impl.data.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl(
    private val favoriteDao: FavoriteDao
) : FavoriteRepository {
    override fun observeFavorites(): Flow<List<Favorite>> {
        return favoriteDao.observeFavorites().map { favItem ->
            favItem.map { it.toDomain() }
        }
    }

    override suspend fun addFavorite(favorite: Favorite) {
        favoriteDao.insertFavorite(favorite.toEntity())
    }

    override suspend fun getFavoriteCount(): Int {
         return favoriteDao.getFavoriteCount()
    }
}