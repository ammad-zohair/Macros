package com.ammad.macros.navigation

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.ammad.dashboard_api.splash.api.DashboardRoute
import com.ammad.navigation.AppNavigator
import com.example.design_system.component.BottomNavItem

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

    override fun navigateToBottomNavItem(item: BottomNavItem) {
        when (item) {
            BottomNavItem.SEARCH -> navigateTo(DashboardRoute)
            else -> {}
            //BottomNavItem.FAVORITES -> navigateTo(FavoritesRoute)
            //BottomNavItem.LOG -> navigateTo(LogRoute)
        }
    }
}