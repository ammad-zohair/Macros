package com.ammad.dashboard_impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.ammad.dashboard_api.splash.api.DashboardRoute
import com.ammad.dashboard_impl.presentation.DashboardEffect
import com.ammad.dashboard_impl.presentation.DashboardIntent
import com.ammad.dashboard_impl.presentation.DashboardScreen
import com.ammad.dashboard_impl.presentation.DashboardViewModel
import com.ammad.shared.BaseScreen
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.dashboardEntry() {
    entry<DashboardRoute> {
        val viewModel: DashboardViewModel = koinViewModel()
        BaseScreen(
            viewModel = viewModel,
            onEffect = { effect ->
                when (effect) {
                    is DashboardEffect.ShowToast -> {}
                    is DashboardEffect.NavigateTo -> {}
                }
            },
            onErrorDismiss = { viewModel.onIntent(DashboardIntent.DismissError) }
        ) { state, onIntent ->
            DashboardScreen(state, onIntent)
        }
    }
}