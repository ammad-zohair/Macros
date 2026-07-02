package com.ammad.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

interface AppNavigator {
    val backStack: NavBackStack<NavKey>
    fun navigateTo(key: NavKey)
    fun goBack(): Boolean
    fun navigateAndClearStack(key: NavKey)
}