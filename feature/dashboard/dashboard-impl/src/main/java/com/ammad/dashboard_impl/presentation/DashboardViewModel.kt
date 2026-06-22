package com.ammad.dashboard_impl.presentation

import androidx.lifecycle.viewModelScope
import com.ammad.dashboard_impl.domain.repository.DashboardRepository
import com.ammad.navigation.AppNavigator
import com.ammad.network.ApiResult
import com.ammad.shared.BaseViewModel
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val appNavigator: AppNavigator,
    private val dashboardRepo: DashboardRepository

) : BaseViewModel<DashboardState, DashboardIntent, DashboardEffect>(DashboardState()) {

    override fun onIntent(intent: DashboardIntent) {
        when (intent) {
            is DashboardIntent.Search -> { searchFood() }
            is DashboardIntent.GetFoodItem -> {

            }
        }
    }

    private fun searchFood() {
        setState { copy(isLoading = true, errorMessage = null) }
        viewModelScope.launch {
            try {
                when (val result = dashboardRepo.searchFood(state.value.searchQuery)) {
                    is ApiResult.Success -> {
                        setState { copy(searchItems = result.data) }
                    }
                    is ApiResult.Error -> {
                        setState { copy(isLoading = false, errorMessage = result.exception.message) }
                    }
                }
            } catch (e: Exception) {
                setState { copy(isLoading = false, errorMessage = e.message) }
            } finally {
                setState { copy(isLoading = false) }
            }
        }
    }
}