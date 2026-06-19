package com.ammad.navigation

import androidx.navigation3.runtime.NavKey

interface AppNavigator {
    fun navigateTo(key: NavKey)
    fun goBack(): Boolean
    fun navigateAndClearStack(key: NavKey)
}