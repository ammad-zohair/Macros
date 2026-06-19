package com.ammad.macros

import android.app.Application
import com.ammad.dashboard_impl.di.dashboardModule
import com.ammad.macros.di.appModule
import com.ammad.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                dashboardModule,
                appModule
            )
        }
    }
}