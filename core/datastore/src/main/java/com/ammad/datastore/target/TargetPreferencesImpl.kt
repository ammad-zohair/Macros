package com.ammad.datastore.target

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

internal val Context.targetDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "target"
)

private object TargetPreferencesKey {
    val CALORIES = intPreferencesKey("target_calories")
    val PROTEIN = intPreferencesKey("target_protein")
    val CARBOHYDRATES = intPreferencesKey("target_carbohydrates")
}

class TargetPreferencesImpl(
    private val dataStore: DataStore<Preferences>,
) : TargetPreferences {
    override fun observeTargets(): Flow<Target> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            Target(
                calories = preferences[TargetPreferencesKey.CALORIES] ?: 0,
                protein = preferences[TargetPreferencesKey.PROTEIN] ?: 0,
                carbohydrates = preferences[TargetPreferencesKey.CARBOHYDRATES] ?: 0
            )
        }
    }

    override suspend fun updateTarget(target: Target) {
        dataStore.edit { preferences ->
            preferences[TargetPreferencesKey.CALORIES] = target.calories
            preferences[TargetPreferencesKey.PROTEIN] = target.protein
            preferences[TargetPreferencesKey.CARBOHYDRATES] = target.carbohydrates
        }
    }

    override suspend fun clearTarget() {
        dataStore.edit { preferences -> preferences.clear() }
    }
}