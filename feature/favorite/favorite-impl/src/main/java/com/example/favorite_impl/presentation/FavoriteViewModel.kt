package com.example.favorite_impl.presentation

import androidx.lifecycle.viewModelScope
import com.ammad.dashboard_api.splash.api.DashboardRoute
import com.ammad.shared.base.BaseViewModel
import com.example.favorite_api.domain.model.Favorite
import com.example.favorite_api.domain.repository.FavoriteRepository
import com.example.favorite_impl.data.mapper.toLog
import com.example.log_api.domain.repository.LogRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val favoriteRepository: FavoriteRepository,
    private val logRepository: LogRepository
) : BaseViewModel<FavoriteState, FavoriteIntent, FavoriteEffect>(FavoriteState()) {

    init {
        fetchFavorites()
        fetchFavoriteCount()
    }

    override fun onIntent(intent: FavoriteIntent) {
        when (intent) {
            is FavoriteIntent.DismissError -> setState { copy(errorMessage = null) }
            is FavoriteIntent.ExploreFoods -> navigate(route = DashboardRoute, clearBackStack = true)
            is FavoriteIntent.DeleteFavorite -> deleteFavorite(intent.favorite)
            is FavoriteIntent.AddToLog -> addToLog(intent.favorite)
        }
    }

    private fun fetchFavorites() {
        viewModelScope.launch {
            try {
                setState { copy(isLoading = true, errorMessage = null) }
                favoriteRepository.observeFavorites().collect { favoritesList ->
                    setState { copy(favorites = favoritesList, isLoading = false) }
                }
            } catch (e: Exception) {
                setState { copy(errorMessage = e.message, isLoading = false) }
            }
        }
    }

    private fun fetchFavoriteCount() {
        viewModelScope.launch {
            try {
                setState { copy(isLoading = true, errorMessage = null) }
                val count = favoriteRepository.getFavoriteCount()
                setState { copy(favoriteCount = count) }
            } catch (e: Exception) {
                setState { copy(errorMessage = e.message) }
            } finally {
                setState { copy(isLoading = false) }
            }
        }
    }

    private fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch {
            try {
                setState { copy(isLoading = true, errorMessage = null) }
                favoriteRepository.deleteFavorite(favorite)
                fetchFavoriteCount()
                showToast("Deleted ${favorite.description} from favorites")
            } catch (e: Exception) {
                setState { copy(errorMessage = e.message) }
            } finally {
                setState { copy(isLoading = false) }
            }
        }
    }

    private fun addToLog(favorite: Favorite) {
        viewModelScope.launch {
            try {
                setState { copy(isLoading = true, errorMessage = null) }
                logRepository.insertLog(favorite.toLog())
                showToast("Added ${favorite.description} to log")
            } catch (e: Exception) {
                setState { copy(errorMessage = e.message) }
            } finally {
                setState { copy(isLoading = false) }
            }
        }
    }

}