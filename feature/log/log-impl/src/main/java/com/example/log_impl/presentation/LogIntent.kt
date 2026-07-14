package com.example.log_impl.presentation

import com.ammad.shared.base.BaseIntent

sealed interface LogIntent : BaseIntent {
    data object DismissError : LogIntent
    data object PreviousDay : LogIntent
    data object NextDay : LogIntent
    data object GoBack : LogIntent
    data object AddFood : LogIntent
    data class ProteinTargetChange(val protein: Float) : LogIntent
    data class CarbohydrateTargetChange(val carb: Float) : LogIntent
}