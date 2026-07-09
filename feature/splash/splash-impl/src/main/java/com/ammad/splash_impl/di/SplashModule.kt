package com.ammad.splash_impl.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.ammad.splash_impl.navigation.splashEntry
import com.ammad.splash_impl.presentation.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val splashModule = module {
    viewModel { SplashViewModel() }
    single<EntryProviderScope<NavKey>.() -> Unit>(named("splashEntry")) { { splashEntry(get()) } }
}
