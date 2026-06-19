package com.ammad.dashboard_impl.presentation

import com.ammad.shared.BaseEffect

sealed interface DashboardEffect : BaseEffect {
    data class ShowToast(val message: String) : DashboardEffect
    data class NavigateTo(val route: String) : DashboardEffect
}