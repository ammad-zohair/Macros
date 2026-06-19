package com.ammad.dashboard_impl.presentation

import androidx.lifecycle.viewModelScope
import com.ammad.dashboard_impl.domain.repository.DashboardRepository
import com.ammad.navigation.AppNavigator
import com.ammad.shared.BaseViewModel
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val appNavigator: AppNavigator,
    private val dashboardRepo: DashboardRepository

) : BaseViewModel<DashboardState, DashboardIntent, DashboardEffect>(DashboardState()) {

    override fun onIntent(intent: DashboardIntent) {
        when (intent) {
            is DashboardIntent.Search -> {
                viewModelScope.launch {

                }

            }
            is DashboardIntent.GetFoodItem -> {

            }
        }
    }

    private fun searchFood() {
        viewModelScope.launch {
           // setState { copy(isLoading = true, error = null) }

        }
    }
}