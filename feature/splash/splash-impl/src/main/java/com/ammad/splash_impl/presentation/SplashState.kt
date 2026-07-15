package com.ammad.splash_impl.presentation

import com.ammad.shared.base.BaseState

data class SplashState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val showOnboarding: Boolean = false
) : BaseState