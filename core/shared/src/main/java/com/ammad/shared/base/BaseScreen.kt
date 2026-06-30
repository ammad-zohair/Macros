package com.ammad.shared.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.example.design_system.component.AppToast
import com.example.design_system.component.ErrorBanner
import com.example.design_system.component.LoadingOverlay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun <S : BaseState, I : BaseIntent, E : BaseEffect> BaseScreen(
    viewModel: BaseViewModel<S, I, E>,
    onEffect: (E) -> Unit = {},
    onErrorDismiss: () -> Unit = {},
    content: @Composable (state: S, onIntent: (I) -> Unit) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var toastMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        launch {
            viewModel.effect.collect(onEffect)
        }
        launch {
            viewModel.toast.collect { message ->
                toastMessage = message
                delay(2000.milliseconds)
                toastMessage = null
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        content(state) {
            viewModel.onIntent(it)
        }
        ErrorBanner(
            message = state.errorMessage,
            onDismiss = onErrorDismiss,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        LoadingOverlay(visible = state.isLoading)
        AppToast(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp, start = 16.dp, end = 16.dp),
            message = toastMessage
        )
    }
}