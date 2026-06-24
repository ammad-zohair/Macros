package com.ammad.splash_impl.presentation

import com.ammad.dashboard_api.splash.api.DashboardRoute
import com.ammad.navigation.AppNavigator
import com.ammad.shared.BaseViewModel

class SplashViewModel(
    private val appNavigator: AppNavigator
) : BaseViewModel<SplashState, SplashIntent, SplashEffect>(SplashState())
{
    override fun onIntent(intent: SplashIntent) {
        when (intent) {
            SplashIntent.OnGetStartedClicked -> {
                appNavigator.navigateAndClearStack(DashboardRoute)
            }
        }
    }
}