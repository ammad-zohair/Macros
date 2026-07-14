package com.ammad.dashboard_impl.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ammad.dashboard_impl.domain.model.FoodItem
import com.ammad.dashboard_impl.domain.model.FoodSearchItem
import com.ammad.dashboard_impl.presentation.components.FoodDetailCard
import com.ammad.dashboard_impl.presentation.components.FoodSearchBar
import com.example.design_system.components.EmptyView

@Composable
fun DashboardScreen(
    paddingValues: PaddingValues,
    state: DashboardState,
    onIntent: (DashboardIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    FoodDetailScreen(
        foodItem = state.foodItem,
        query = state.searchQuery,
        onQueryChange = { onIntent(DashboardIntent.Search(it)) },
        searchResult = state.searchItems,
        onSearchItemClick = { onIntent(DashboardIntent.GetFoodItem(it)) },
        isFavorite = state.isFavorite,
        onFavoriteToggle = { onIntent(DashboardIntent.ToggleFavorite(it)) },
        onAddToLog = { onIntent(DashboardIntent.AddToLog(it)) },
        modifier = modifier.padding(paddingValues)
    )
}

@Composable
fun FoodDetailScreen(
    foodItem: FoodItem,
    query: String,
    onQueryChange: (String) -> Unit,
    searchResult: FoodSearchItem,
    onSearchItemClick: (Int) -> Unit,
    isFavorite: Boolean,
    onFavoriteToggle: (FoodItem) -> Unit,
    onAddToLog: (FoodItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        FoodSearchBar(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = onQueryChange,
            searchResults = searchResult.foods,
            onResultClick = onSearchItemClick,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (foodItem.fdcId == 0) EmptyView("No food selected")
            AnimatedVisibility(visible = foodItem.fdcId != 0) {
                FoodDetailCard(
                    foodItem = foodItem,
                    isFavorite = isFavorite,
                    onFavoriteToggle = onFavoriteToggle,
                    onAddToLog = onAddToLog
                )
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}