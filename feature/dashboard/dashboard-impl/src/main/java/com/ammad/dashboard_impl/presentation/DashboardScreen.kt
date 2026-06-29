package com.ammad.dashboard_impl.presentation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ammad.dashboard_impl.domain.model.FoodItem
import com.ammad.dashboard_impl.domain.model.FoodSearchItem
import com.ammad.dashboard_impl.presentation.component.FoodDetailCard
import com.ammad.dashboard_impl.presentation.component.FoodSearchBar
import com.example.design_system.component.CustomTopBar
import com.example.design_system.component.EmptyView

@Composable
fun DashboardScreen(
    state: DashboardState,
    onIntent: (DashboardIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    FoodDetailScreen(
        foodItem = state.foodItem,
        query = state.searchQuery,
        onQueryChange = {
            Log.d("DashboardScreen", "onQueryChange: $it")
            onIntent(DashboardIntent.Search(it)) },
        searchResult = state.searchItems,
        onSearchItemClick = {
            Log.d("DashboardScreen", "onItemclick: $it")
            onIntent(DashboardIntent.GetFoodItem(it)) },
        isFavorite = state.isFavorite,
        onFavoriteToggle = { onIntent(DashboardIntent.ToggleFavorite) },
        onAddToLog = { onIntent(DashboardIntent.AddToLog) },
        modifier = modifier
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
    onFavoriteToggle: () -> Unit,
    onAddToLog: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { CustomTopBar(title = "Macros") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
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
                if (foodItem.fdcId == 0) EmptyView()
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
}