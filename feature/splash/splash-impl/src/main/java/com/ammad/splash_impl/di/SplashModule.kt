package com.ammad.splash_impl.di

import com.ammad.splash_impl.presentation.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val splashModule = module {
    viewModel { SplashViewModel(get(), get()) }
}