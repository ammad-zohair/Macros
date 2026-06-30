package com.ammad.splash_impl.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.ammad.shared.base.BaseScreen
import com.ammad.splash_api.splash.api.SplashRoute
import com.ammad.splash_impl.presentation.SplashEffect
import com.ammad.splash_impl.presentation.SplashScreen
import com.ammad.splash_impl.presentation.SplashViewModel
import org.koin.compose.viewmodel.koinViewModel

fun EntryProviderScope<NavKey>.splashEntry() {
    entry<SplashRoute> {
        val viewModel: SplashViewModel = koinViewModel()
        BaseScreen(
            viewModel = viewModel,
            onEffect = {
                when (it) {
                    is SplashEffect.NavigateTo -> {}
                }
            }
        ) { state, onIntent ->
            SplashScreen(
                state = state,
                onIntent = onIntent
            )
        }
    }
}
