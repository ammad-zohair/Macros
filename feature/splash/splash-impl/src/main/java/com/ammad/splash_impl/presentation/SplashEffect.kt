package com.ammad.splash_impl.presentation

import com.ammad.shared.base.BaseEffect

sealed interface SplashEffect : BaseEffect {
    data class NavigateTo(val route: String) : SplashEffect
}