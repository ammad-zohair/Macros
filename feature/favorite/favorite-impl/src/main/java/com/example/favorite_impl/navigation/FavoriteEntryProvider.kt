package com.example.favorite_impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.ammad.navigation.AppNavigator
import com.ammad.shared.base.BaseScreen
import com.example.design_system.components.BottomNavItem
import com.example.favorite_api.FavoriteRoute
import com.example.favorite_impl.presentation.FavoriteIntent
import com.example.favorite_impl.presentation.FavoriteScreen
import com.example.favorite_impl.presentation.FavoriteViewModel
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.favoriteEntry(appNavigator: AppNavigator) {
    entry<FavoriteRoute> {
        val viewModel: FavoriteViewModel = koinViewModel()
        BaseScreen(
            viewModel = viewModel,
            appNavigator = appNavigator,
            selectedBottomNavItem = BottomNavItem.FAVORITES,
            onEffect = {},
            onErrorDismiss = { viewModel.onIntent(FavoriteIntent.DismissError) }
        ) { paddingValues, state, onIntent ->
            FavoriteScreen(paddingValues, state, onIntent)
        }
    }
}