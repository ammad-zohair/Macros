package com.ammad.dashboard_impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.ammad.dashboard_api.splash.api.DashboardRoute
import com.ammad.dashboard_impl.presentation.DashboardIntent
import com.ammad.dashboard_impl.presentation.DashboardScreen
import com.ammad.dashboard_impl.presentation.DashboardViewModel
import com.ammad.navigation.AppNavigator
import com.ammad.shared.base.BaseScreen
import com.example.design_system.components.BottomNavItem
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.dashboardEntry(appNavigator: AppNavigator) {
    entry<DashboardRoute> {
        val viewModel: DashboardViewModel = koinViewModel()
        BaseScreen(
            viewModel = viewModel,
            appNavigator = appNavigator,
            selectedBottomNavItem = BottomNavItem.SEARCH,
            onEffect = {},
            onErrorDismiss = { viewModel.onIntent(DashboardIntent.DismissError) }
        ) { paddingValues, state, onIntent ->
            DashboardScreen(paddingValues, state, onIntent)
        }
    }
}