package com.ammad.macros.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.ammad.navigation.AppNavigator

class AppNavigatorImpl(initialRoute: NavKey) : AppNavigator {

    override val backStack = NavBackStack(initialRoute)

    override fun navigateTo(key: NavKey) {
        backStack.add(key)
    }

    override fun goBack(): Boolean {
        return backStack.removeLastOrNull() != null
    }

    override fun navigateAndClearStack(key: NavKey) {
        backStack.clear()
        backStack.add(key)
    }
}