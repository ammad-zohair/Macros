package com.ammad.splash_impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.ammad.navigation.AppNavigator
import com.ammad.shared.base.BaseScreen
import com.ammad.splash_api.splash.api.SplashRoute
import com.ammad.splash_impl.presentation.SplashScreen
import com.ammad.splash_impl.presentation.SplashViewModel
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.splashEntry(appNavigator: AppNavigator) {
    entry<SplashRoute> {
        val viewModel: SplashViewModel = koinViewModel()
        BaseScreen(
            viewModel = viewModel,
            appNavigator = appNavigator,
            showAppBars = false,
            onEffect = {}
        ) { paddingValues, state, onIntent ->
            SplashScreen(
                paddingValues = paddingValues,
                state = state,
                onIntent = onIntent
            )
        }
    }
}
