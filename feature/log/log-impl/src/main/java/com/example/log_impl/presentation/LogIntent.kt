package com.example.log_impl.presentation

import com.ammad.shared.base.BaseIntent
import com.example.log_api.domain.model.LogEntryDisplay

sealed interface LogIntent : BaseIntent {
    data object DismissError : LogIntent
    data object PreviousDay : LogIntent
    data object NextDay : LogIntent
    data object GoBack : LogIntent
    data object AddFood : LogIntent
    data class ProteinTargetChange(val protein: Float) : LogIntent
    data class CarbohydrateTargetChange(val carb: Float) : LogIntent
    data class CaloriesTargetChange(val calories: Float) : LogIntent
    data object OnEditClick : LogIntent
    data class OnDeleteClick(val entry: LogEntryDisplay) : LogIntent
}