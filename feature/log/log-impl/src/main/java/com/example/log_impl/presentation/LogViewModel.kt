package com.example.log_impl.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ammad.dashboard_api.splash.api.DashboardRoute
import com.ammad.shared.base.BaseViewModel
import com.example.log_api.LogRoute
import com.example.log_api.domain.repository.LogRepository
import com.example.log_impl.data.mapper.toMacroDisplayList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class LogViewModel(
    private val logRepository: LogRepository
) : BaseViewModel<LogState, LogIntent, LogEffect>(LogState()) {

    private val selectedDate = MutableStateFlow(LocalDate.now())

    init {
        observeDailyMacroTotals()
        observeLogEntries()
    }

    override fun onIntent(intent: LogIntent) {
        when (intent) {
            is LogIntent.DismissError -> setState { copy(errorMessage = null) }
            is LogIntent.NextDay -> { changeDate { plusDays(1) }}
            is LogIntent.PreviousDay -> { changeDate { minusDays(1) }}
            is LogIntent.GoBack -> {
                setState { LogState() }
                selectedDate.value = LocalDate.now()
            }
            is LogIntent.AddFood -> { navigate(route = DashboardRoute, clearBackStack = true) }
            is LogIntent.CarbohydrateTargetChange -> { setState { copy(targetCarbohydrates = intent.carb.toInt()) } }
            is LogIntent.ProteinTargetChange -> { setState { copy(targetProtein = intent.protein.toInt()) } }
        }

    }

    private fun observeLogEntries() {
        viewModelScope.launch {
            selectedDate
                .flatMapLatest { date -> logRepository.observeLogs(date) }
                .catch { e -> setState { copy(errorMessage = e.message, isLoading = false) } }
                .collect { entries -> setState { copy(entries = entries, isLoading = false) } }
        }
    }

    private fun observeDailyMacroTotals() {
        viewModelScope.launch {
            selectedDate
                .flatMapLatest { date -> logRepository.observeDailyTotals(date) }
                .catch { e -> setState { copy(errorMessage = e.message, isLoading = false) } }
                .collect { totals ->
                    setState {
                        copy(
                            macros = totals.toMacroDisplayList(),
                            consumedCalories = totals.totalCalories.toInt(),
                            calorieProgress = (totals.totalCalories.toFloat()/targetCalories),
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun changeDate(transform: LocalDate.() -> LocalDate) {
        val newDate = selectedDate.value.transform()
        setState { copy(selectedDate = newDate, isLoading = true) }
        selectedDate.value = newDate
    }
}