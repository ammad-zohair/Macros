package com.ammad.datastore.target

import kotlinx.coroutines.flow.Flow

interface TargetPreferences {
    fun observeTargets(): Flow<Target>
    suspend fun updateTarget(target: Target)
    suspend fun clearTarget()
}