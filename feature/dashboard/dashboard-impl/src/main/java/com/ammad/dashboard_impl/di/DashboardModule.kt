package com.ammad.dashboard_impl.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.ammad.dashboard_impl.data.remote.api.DashboardApiService
import com.ammad.dashboard_impl.data.repository.DashboardRepositoryImpl
import com.ammad.dashboard_impl.domain.repository.DashboardRepository
import com.ammad.dashboard_impl.navigation.dashboardEntry
import com.ammad.dashboard_impl.presentation.DashboardViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val dashboardModule = module {
    single { get<Retrofit>().create(DashboardApiService::class.java) }
    single<DashboardRepository> { DashboardRepositoryImpl(get()) }
    viewModel {
        DashboardViewModel(
            dashboardRepo = get(),
            favoriteRepo = get(),
            logRepo = get()
        )
    }
    single<EntryProviderScope<NavKey>.() -> Unit>(named("dashboardEntry")) { { dashboardEntry(get()) } }
}
