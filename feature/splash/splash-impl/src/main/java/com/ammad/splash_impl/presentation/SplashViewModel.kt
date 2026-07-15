package com.ammad.splash_impl.presentation

import androidx.lifecycle.viewModelScope
import com.ammad.dashboard_api.splash.api.DashboardRoute
import com.ammad.datastore.onboarding.OnboardingPreferences
import com.ammad.shared.base.BaseViewModel
import kotlinx.coroutines.launch

class SplashViewModel(
    private val onboardingPreferences: OnboardingPreferences
) : BaseViewModel<SplashState, SplashIntent, SplashEffect>(SplashState()) {

    init {
        checkOnboardingStatus()
    }

    override fun onIntent(intent: SplashIntent) {
        when (intent) {
            SplashIntent.OnGetStartedClicked -> {
                navigate(
                    route = DashboardRoute,
                    clearBackStack = true
                )
            }
            SplashIntent.OnboardingCompleted -> { completeOnboardingAndProceed() }
        }
    }

    private fun checkOnboardingStatus() {
        viewModelScope.launch {
            if (onboardingPreferences.hasCompletedOnboarding()) {
                navigate(route = DashboardRoute, clearBackStack = true)
            } else {
                setState { copy(showOnboarding = true) }
            }
        }
    }

    private fun completeOnboardingAndProceed() {
        viewModelScope.launch {
            onboardingPreferences.setOnboardingCompleted()
            navigate(route = DashboardRoute, clearBackStack = true)
        }
    }
}