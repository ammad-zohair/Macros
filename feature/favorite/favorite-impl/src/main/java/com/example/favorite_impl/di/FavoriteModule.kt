package com.example.favorite_impl.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.example.favorite_api.domain.repository.FavoriteRepository
import com.example.favorite_impl.data.repository.FavoriteRepositoryImpl
import com.example.favorite_impl.navigation.favoriteEntry
import com.example.favorite_impl.presentation.FavoriteViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named

val favoriteModule = module {
    single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
    single<EntryProviderScope<NavKey>.() -> Unit>(named("favoriteEntry")) { { favoriteEntry(get()) } }
    viewModel { FavoriteViewModel(get(), get()) }
}