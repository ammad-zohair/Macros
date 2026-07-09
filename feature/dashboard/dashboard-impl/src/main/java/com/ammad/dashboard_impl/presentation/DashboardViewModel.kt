package com.ammad.dashboard_impl.presentation

import androidx.lifecycle.viewModelScope
import com.ammad.dashboard_impl.data.mapper.toFavorite
import com.ammad.dashboard_impl.domain.model.FoodItem
import com.ammad.dashboard_impl.domain.model.FoodSearchItem
import com.ammad.dashboard_impl.domain.repository.DashboardRepository
import com.ammad.network.ApiResult
import com.ammad.shared.base.BaseViewModel
import com.example.favorite_api.domain.repository.FavoriteRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@OptIn(FlowPreview::class)
class DashboardViewModel(
    private val dashboardRepo: DashboardRepository,
    private val favoriteRepo: FavoriteRepository
) : BaseViewModel<DashboardState, DashboardIntent, DashboardEffect>(DashboardState()) {

    private val searchQueryFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)

    init {
        viewModelScope.launch {
            searchQueryFlow
                .debounce(500.milliseconds)
                .distinctUntilChanged()
                .collectLatest { query ->
                    searchFood(query)
                }
        }
    }
    override fun onIntent(intent: DashboardIntent) {
        when (intent) {
            is DashboardIntent.Search -> {
                setState { copy(searchQuery = intent.query) }
                processSearchIntent(intent.query)
            }

            is DashboardIntent.GetFoodItem -> {
                getFoodItem(intent.fdcId)
            }

            is DashboardIntent.AddToLog -> {
                showToast("Feature Coming Soon!")
            }

            is DashboardIntent.ToggleFavorite -> {
                if (!state.value.isFavorite) {
                    setState { copy(isFavorite = !isFavorite) }
                    addFoodItemToFavorites(intent.foodItem)
                }
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
                        setState {
                            copy(
                                isLoading = false,
                                errorMessage = result.exception.message
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                setState { copy(isLoading = false, errorMessage = e.message) }
            } finally {
                setState { copy(isLoading = false) }
            }
        }
    }

    private suspend fun searchFood(query: String) {
        try {
            setState { copy(isLoading = true, errorMessage = null) }
            when (val result = dashboardRepo.searchFood(query)) {
                is ApiResult.Success -> {
                    setState { copy(searchItems = result.data) }
                }

                is ApiResult.Error -> {
                    setState {
                        copy(
                            isLoading = false,
                            errorMessage = result.exception.message
                        )
                    }
                }
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            setState { copy(isLoading = false, errorMessage = e.message)}
        } finally {
            setState { copy(isLoading = false) }
        }
    }

    private fun addFoodItemToFavorites(foodItem: FoodItem) {
        viewModelScope.launch {
            try {
                setState { copy(isLoading = true, errorMessage = null) }
                favoriteRepo.addFavorite(foodItem.toFavorite())
                showToast("Added ${foodItem.description} to favorites")
                //setState { copy(isFavorite = true) }
            } catch (e: Exception) {
                setState { copy(errorMessage = e.message) }
            } finally {
                setState { copy(isLoading = false) }
            }

        }

    }

    private fun processSearchIntent(query: String) {
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
        searchQueryFlow.tryEmit(query)
    }
}