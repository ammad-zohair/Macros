package com.ammad.dashboard_impl.presentation

import com.ammad.dashboard_impl.domain.model.FoodItem
import com.ammad.dashboard_impl.domain.model.FoodSearchItem
import com.ammad.shared.BaseState

data class DashboardState(
    val searchItems: List<FoodSearchItem> = emptyList(),
    val foodItem: FoodItem = FoodItem(),
) : BaseState