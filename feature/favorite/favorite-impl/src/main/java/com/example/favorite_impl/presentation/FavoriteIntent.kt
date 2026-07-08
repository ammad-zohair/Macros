package com.example.favorite_impl.presentation

import com.ammad.shared.base.BaseIntent

sealed interface FavoriteIntent : BaseIntent {
    data object DismissError : FavoriteIntent
    data object ExploreFoods : FavoriteIntent
}