package com.ammad.datastore.onboarding

interface OnboardingPreferences {
    suspend fun hasCompletedOnboarding(): Boolean
    suspend fun setOnboardingCompleted()
}