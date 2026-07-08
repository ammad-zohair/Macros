package com.example.favorite_impl.presentation

import com.ammad.shared.base.BaseState
import com.example.favorite_api.domain.model.Favorite

data class FavoriteState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val favorites: List<Favorite> = emptyList(),
    val favoriteCount: Int = 0
) : BaseState