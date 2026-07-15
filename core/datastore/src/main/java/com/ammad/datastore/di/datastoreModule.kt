package com.ammad.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.ammad.datastore.onboarding.OnboardingPreferences
import com.ammad.datastore.onboarding.OnboardingPreferencesImpl
import com.ammad.datastore.onboarding.onboardingDataStore
import com.ammad.datastore.target.TargetPreferences
import com.ammad.datastore.target.TargetPreferencesImpl
import com.ammad.datastore.target.targetDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataStoreModule = module {
    single<DataStore<Preferences>>(named("target")) { androidContext().targetDataStore }
    single<TargetPreferences> { TargetPreferencesImpl(get(named("target"))) }
    single<DataStore<Preferences>>(named("onboarding")) { androidContext().onboardingDataStore }
    single<OnboardingPreferences> { OnboardingPreferencesImpl(get(named("onboarding"))) }
}