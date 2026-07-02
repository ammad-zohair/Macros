package com.ammad.shared.base

import androidx.navigation3.runtime.NavKey

sealed class BaseViewModelEffect {
    data class NavigateTo(val route: NavKey, val clearBackStack: Boolean = false) : BaseViewModelEffect()
    data class ShowToast(val message: String) : BaseViewModelEffect()
    data object ShowLoader : BaseViewModelEffect()
    data object HideKeyboard : BaseViewModelEffect()
    data class ShowError(val message: String) : BaseViewModelEffect()
}