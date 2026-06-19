package com.ammad.dashboard_impl.presentation

import com.ammad.shared.BaseIntent

sealed interface DashboardIntent : BaseIntent {
    data class Search(val query: String) : DashboardIntent
    data class GetFoodItem(val fdcId: Int) : DashboardIntent
}