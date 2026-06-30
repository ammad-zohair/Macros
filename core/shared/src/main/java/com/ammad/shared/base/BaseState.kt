package com.ammad.shared.base

interface BaseState {
    val isLoading: Boolean
        get() = false
    val errorMessage: String?
        get() = null
}