package com.ammad.macros

import android.app.Application
import com.ammad.dashboard_impl.di.dashboardModule
import com.ammad.datastore.di.dataStoreModule
import com.ammad.macros.di.appModule
import com.ammad.network.di.networkModule
import com.ammad.splash_impl.di.splashModule
import com.example.database.di.databaseModule
import com.example.favorite_impl.di.favoriteModule
import com.example.log_impl.di.logModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                databaseModule,
                dataStoreModule,
                dashboardModule,
                splashModule,
                favoriteModule,
                logModule,
                appModule
            )
        }
    }
}