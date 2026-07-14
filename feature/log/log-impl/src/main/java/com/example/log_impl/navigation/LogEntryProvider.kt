package com.example.log_impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.ammad.navigation.AppNavigator
import com.ammad.shared.base.BaseScreen
import com.example.design_system.components.BottomNavItem
import com.example.log_api.LogRoute
import com.example.log_impl.presentation.LogIntent
import com.example.log_impl.presentation.LogScreen
import com.example.log_impl.presentation.LogViewModel
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.logEntry(appNavigator: AppNavigator) {
    entry<LogRoute> {
        val viewModel: LogViewModel = koinViewModel()
        BaseScreen(
            viewModel = viewModel,
            appNavigator = appNavigator,
            selectedBottomNavItem = BottomNavItem.LOG,
            onEffect = {},
            onErrorDismiss = { viewModel.onIntent(LogIntent.DismissError) }
        ) { paddingValues, state, onIntent ->
            LogScreen(paddingValues, state, onIntent)
        }
    }
}