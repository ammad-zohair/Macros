package com.ammad.shared.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation3.runtime.NavKey
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : BaseState, I : BaseIntent, E : BaseEffect>(
    initialState: S,
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<E>()
    val effect: SharedFlow<E> = _effect.asSharedFlow()

    private val _baseUIEffect: MutableSharedFlow<BaseViewModelEffect> = MutableSharedFlow()
    val baseUIEffect = _baseUIEffect.asSharedFlow()


    protected fun setState(reducer: S.() -> S) {
        _state.update(reducer)
    }

    protected fun sendEffect(effect: E) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    protected fun navigate(route: NavKey, clearBackStack: Boolean) {
        viewModelScope.launch {
            _baseUIEffect.emit(BaseViewModelEffect.NavigateTo(route, clearBackStack))
        }
    }

    protected fun showToast(message: String) {
        viewModelScope.launch {
            _baseUIEffect.emit(BaseViewModelEffect.ShowToast(message))
        }
    }

    abstract fun onIntent(intent: I)

}