package com.ammad.shared.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ammad.navigation.AppNavigator
import com.example.design_system.components.AppToast
import com.example.design_system.components.BottomNavItem
import com.example.design_system.components.CustomBottomNavigationBar
import com.example.design_system.components.CustomTopBar
import com.example.design_system.components.ErrorBanner
import com.example.design_system.components.LoadingOverlay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun <S : BaseState, I : BaseIntent, E : BaseEffect> BaseScreen(
    viewModel: BaseViewModel<S, I, E>,
    appNavigator: AppNavigator,
    showAppBars: Boolean = true,
    selectedBottomNavItem: BottomNavItem? = null,
    onEffect: (E) -> Unit = {},
    onErrorDismiss: () -> Unit = {},
    content: @Composable (paddingValues: PaddingValues, state: S, onIntent: (I) -> Unit) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var toastMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        launch {
            viewModel.effect.collect { effect ->
                onEffect(effect)
            }
        }
        launch {
            viewModel.baseUIEffect.collect { effect ->
                when (effect) {
                    is BaseViewModelEffect.ShowToast -> {
                        toastMessage = effect.message
                        delay(2000.milliseconds)
                        toastMessage = null
                    }

                    is BaseViewModelEffect.NavigateTo -> {
                        if (effect.clearBackStack)
                            appNavigator.navigateAndClearStack(effect.route)
                        else
                            appNavigator.navigateTo(effect.route)
                    }
                }
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { if (showAppBars) CustomTopBar(title = "Macros") },
        bottomBar = {
            if (showAppBars && selectedBottomNavItem != null) {
                CustomBottomNavigationBar(
                    selectedItem = selectedBottomNavItem,
                    onItemSelected = { appNavigator.navigateToBottomNavItem(it) }
                )
            }
        },
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            content(innerPadding, state) {
                viewModel.onIntent(it)
            }
            ErrorBanner(
                message = state.errorMessage,
                onDismiss = onErrorDismiss,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = innerPadding.calculateTopPadding())
            )
            LoadingOverlay(visible = state.isLoading)
            AppToast(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        bottom = 24.dp + innerPadding.calculateBottomPadding(),
                        start = 16.dp,
                        end = 16.dp
                    ),
                message = toastMessage
            )
        }
    }
}