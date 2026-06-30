package com.ammad.splash_impl.presentation

import com.ammad.shared.base.BaseIntent

sealed interface SplashIntent : BaseIntent {
    data object OnGetStartedClicked : SplashIntent
}