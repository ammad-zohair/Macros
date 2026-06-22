package com.ammad.shared

interface BaseState {
    val isLoading: Boolean
        get() = false
    val errorMessage: String?
        get() = null
}