package com.example.database.di

import androidx.room.Room
import com.example.database.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDataBase::class.java,
            name = "macros.db"
        ).build()
    }
    single { get<AppDataBase>().favoriteDao() }
    single { get<AppDataBase>().logDao() }
}