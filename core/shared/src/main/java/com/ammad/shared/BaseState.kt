package com.ammad.shared

interface BaseState {
    val isLoading: Boolean
        get() = false
    val isSuccess: Boolean
        get() = false
}