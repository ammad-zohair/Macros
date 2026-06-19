package com.ammad.macros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.ammad.macros.navigation.AppNavigatorImpl
import com.ammad.macros.ui.theme.MacrosTheme
import com.ammad.navigation.AppNavigator
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val appNavigator: AppNavigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MacrosTheme {
                val koin = getKoin()
                val entryBuilders = remember {
                    koin.getAll<EntryProviderScope<NavKey>.() -> Unit>()
                }
                val backStack = (appNavigator as AppNavigatorImpl).backStack

                NavDisplay(
                    backStack = backStack,
                    onBack = {
                        appNavigator.goBack()
                    },
                    entryDecorators = listOf(
                        rememberSaveableStateHolderNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator()
                    ),
                    entryProvider = entryProvider {
                        entryBuilders.forEach {
                            builder -> this.builder()
                        }
                    }
                )
            }
        }
    }
}