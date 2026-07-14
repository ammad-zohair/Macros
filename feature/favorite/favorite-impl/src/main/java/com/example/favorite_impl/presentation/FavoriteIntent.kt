package com.example.favorite_impl.presentation

import com.ammad.shared.base.BaseIntent
import com.example.favorite_api.domain.model.Favorite

sealed interface FavoriteIntent : BaseIntent {
    data object DismissError : FavoriteIntent
    data object ExploreFoods : FavoriteIntent
    data class DeleteFavorite(val favorite: Favorite) : FavoriteIntent
    data class AddToLog(val favorite: Favorite) : FavoriteIntent
}