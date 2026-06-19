package com.ammad.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun <S : BaseState, I : BaseIntent, E : BaseEffect> BaseScreen(
    viewModel: BaseViewModel<S, I, E>,
    onEffect: (E) -> Unit = {},
    content: @Composable (state: S, onIntent: (I) -> Unit) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect(onEffect)
    }
    content(state) {
        viewModel.onIntent(it)
    }

}