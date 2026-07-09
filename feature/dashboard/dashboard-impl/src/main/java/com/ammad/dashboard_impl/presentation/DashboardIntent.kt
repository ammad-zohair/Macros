package com.ammad.dashboard_impl.presentation

import com.ammad.dashboard_impl.domain.model.FoodItem
import com.ammad.shared.base.BaseIntent

sealed interface DashboardIntent : BaseIntent {
    data class Search(val query: String) : DashboardIntent
    data class GetFoodItem(val fdcId: Int) : DashboardIntent
    data class ToggleFavorite(val foodItem: FoodItem) : DashboardIntent
    data object AddToLog : DashboardIntent
    data object DismissError : DashboardIntent
}