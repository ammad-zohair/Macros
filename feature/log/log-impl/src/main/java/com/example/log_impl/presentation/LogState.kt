package com.example.log_impl.presentation

import com.ammad.shared.base.BaseState
import com.example.log_api.domain.model.Log
import com.example.log_api.domain.model.MacroDisplay
import java.time.LocalDate

data class LogState(
    override val isLoading: Boolean = false,
    override val errorMessage: String? = null,
    val selectedDate: LocalDate = LocalDate.now(),
    val consumedCalories: Int = 0,
    val targetCalories: Int = 2400,
    val targetProtein: Int = 100,
    val targetCarbohydrates: Int = 100,
    val calorieProgress: Float = 0.0f,
    val isEdit: Boolean = false,
    val macros: List<MacroDisplay> = emptyList(),
    val entries: List<Log> = emptyList()
) : BaseState
