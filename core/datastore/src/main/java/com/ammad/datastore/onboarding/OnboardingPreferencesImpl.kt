package com.ammad.datastore.onboarding

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

internal val Context.onboardingDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "onboarding"
)

private object OnboardingKeys {
    val HAS_COMPLETED_ONBOARDING = booleanPreferencesKey("has_completed_onboarding")
}

class OnboardingPreferencesImpl(
    private val dataStore: DataStore<Preferences>
) : OnboardingPreferences {
    override suspend fun hasCompletedOnboarding(): Boolean {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[OnboardingKeys.HAS_COMPLETED_ONBOARDING] ?: false
        }.first()
    }

    override suspend fun setOnboardingCompleted() {
        dataStore.edit { preferences ->
            preferences[OnboardingKeys.HAS_COMPLETED_ONBOARDING] = true
        }
    }
}