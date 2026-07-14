package com.example.log_impl.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.log_api.domain.repository.LogRepository
import com.example.log_impl.data.repository.LogRepositoryImpl
import com.example.log_impl.navigation.logEntry
import com.example.log_impl.presentation.LogViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val logModule = module {
    single<LogRepository> { LogRepositoryImpl(get()) }
    viewModel { LogViewModel(get()) }
    single<EntryProviderScope<NavKey>.() -> Unit>(named("logEntry")) { { logEntry(get()) } }
}