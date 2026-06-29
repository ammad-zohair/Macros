package com.ammad.dashboard_impl.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ammad.dashboard_impl.domain.model.FoodSearchItem
import com.ammad.dashboard_impl.domain.repository.DashboardRepository
import com.ammad.navigation.AppNavigator
import com.ammad.network.ApiResult
import com.ammad.shared.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class DashboardViewModel(
    private val appNavigator: AppNavigator,
    private val dashboardRepo: DashboardRepository

) : BaseViewModel<DashboardState, DashboardIntent, DashboardEffect>(DashboardState()) {

    private var searchJob: Job? = null

    override fun onIntent(intent: DashboardIntent) {
        when (intent) {
            is DashboardIntent.Search -> {
                setState { copy(searchQuery = intent.query) }
                searchFood(intent.query)
            }
            is DashboardIntent.GetFoodItem -> {
                Log.d("DashboardViewModel", "onIntent: $intent")
                getFoodItem(intent.fdcId)
            }
            is DashboardIntent.AddToLog -> {
                showToast("Feature Coming Soon!")
            }
            is DashboardIntent.ToggleFavorite -> {
                setState { copy(isFavorite = !isFavorite) }
            }
            is DashboardIntent.DismissError -> {
                setState { copy(errorMessage = null) }
            }
        }
    }

    private fun getFoodItem(fdcId: Int) {
        viewModelScope.launch {
            try {
                setState { copy(isLoading = true, errorMessage = null) }
                when (val result = dashboardRepo.getFood(fdcId)) {
                    is ApiResult.Success -> {
                        showToast("Fetched food item: ${result.data.description}")
                        setState { copy(foodItem = result.data) }
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
    private fun searchFood(query: String) {
        searchJob?.cancel()

        if (query.trim().length < 3) {
            setState {
                copy(
                    searchItems = FoodSearchItem(),
                    isLoading = false,
                    errorMessage = null
                )
            }
            return
        }

        searchJob = viewModelScope.launch {
            try {
                delay(500.milliseconds)
                setState { copy(isLoading = true, errorMessage = null) }
                when (val result = dashboardRepo.searchFood(query)) {
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