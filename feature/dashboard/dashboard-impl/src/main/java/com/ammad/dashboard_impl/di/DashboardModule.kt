package com.ammad.dashboard_impl.di

import com.ammad.dashboard_impl.data.remote.api.DashboardApiService
import com.ammad.dashboard_impl.data.repository.DashboardRepositoryImpl
import com.ammad.dashboard_impl.domain.repository.DashboardRepository
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel
import retrofit2.Retrofit

val dashboardModule = module {
    single { get<Retrofit>().create(DashboardApiService::class.java) }
    single<DashboardRepository> { DashboardRepositoryImpl(get()) }
    //viewModel { DashboardViewModel(get()) }
}