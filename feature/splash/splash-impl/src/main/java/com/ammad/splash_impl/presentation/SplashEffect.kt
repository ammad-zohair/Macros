package com.ammad.splash_impl.presentation

import com.ammad.shared.BaseEffect

sealed interface SplashEffect : BaseEffect {
    data class NavigateTo(val route: String) : SplashEffect
}