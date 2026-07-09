package com.ammad.splash_impl.presentation

import com.ammad.dashboard_api.splash.api.DashboardRoute
import com.ammad.navigation.AppNavigator
import com.ammad.shared.base.BaseViewModel

class SplashViewModel : BaseViewModel<SplashState, SplashIntent, SplashEffect>(SplashState()) {
    override fun onIntent(intent: SplashIntent) {
        when (intent) {
            SplashIntent.OnGetStartedClicked -> {
                navigate(
                    route = DashboardRoute,
                    clearBackStack = true
                )
            }
        }
    }
}