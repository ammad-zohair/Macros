package com.ammad.dashboard_impl.presentation

import com.ammad.dashboard_impl.domain.model.FoodItem
import com.ammad.dashboard_impl.domain.model.FoodSearchItem
import com.ammad.shared.BaseState

data class DashboardState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val searchQuery: String = "",
    val searchItems: FoodSearchItem = FoodSearchItem(),
    val foodItem: FoodItem = FoodItem(),
    val isFavorite: Boolean = false
) : BaseState