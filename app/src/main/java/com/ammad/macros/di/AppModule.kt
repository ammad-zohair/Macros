package com.ammad.macros.di

import com.ammad.macros.navigation.AppNavigatorImpl
import com.ammad.navigation.AppNavigator
import org.koin.dsl.module

val appModule = module {
    //single<AppNavigator> { AppNavigatorImpl(initialRoute = SplashRoute) }
}