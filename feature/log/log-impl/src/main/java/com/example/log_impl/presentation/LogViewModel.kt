package com.example.log_impl.presentation

import androidx.lifecycle.viewModelScope
import com.ammad.dashboard_api.splash.api.DashboardRoute
import com.ammad.datastore.target.Target
import com.ammad.datastore.target.TargetPreferences
import com.ammad.shared.base.BaseViewModel
import com.example.log_api.domain.model.LogEntryDisplay
import com.example.log_api.domain.repository.LogRepository
import com.example.log_impl.data.mapper.toDisplay
import com.example.log_impl.data.mapper.toMacroDisplayList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalCoroutinesApi::class)
class LogViewModel(
    private val logRepository: LogRepository,
    private val targetPreferences: TargetPreferences
) : BaseViewModel<LogState, LogIntent, LogEffect>(LogState()) {

    private val selectedDate = MutableStateFlow(LocalDate.now())

    init {
        observeTargets()
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
            is LogIntent.CaloriesTargetChange -> { setState { copy(targetCalories = intent.calories.toInt()) }}
            is LogIntent.OnEditClick -> {
                setState { copy(isEdit = !isEdit) }
                if (!state.value.isEdit) {
                    saveTargets()
                }
            }
            is LogIntent.OnDeleteClick -> { deleteLogEntry(intent.entry) }
        }

    }

    private fun observeTargets() {
        viewModelScope.launch {
            targetPreferences.observeTargets().collect { target ->
                setState {
                    copy(
                        targetCalories = target.calories,
                        targetProtein = target.protein,
                        targetCarbohydrates = target.carbohydrates
                    )
                }
            }
        }
    }

    private fun saveTargets(
        protein: Int = state.value.targetProtein,
        carbohydrates: Int = state.value.targetCarbohydrates,
        calories: Int = state.value.targetCalories
    ) {
        viewModelScope.launch {
            targetPreferences.updateTarget(
                Target(
                    protein = protein,
                    carbohydrates = carbohydrates,
                    calories = calories
                )
            )
        }
        showToast("Changes saved!")
    }

    private fun observeLogEntries() {
        viewModelScope.launch {
            selectedDate
                .flatMapLatest { date -> logRepository.observeLogs(date) }
                .map { entries -> entries.map { it.toDisplay() } }
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
                            macros = totals.toMacroDisplayList(
                                targetProtein = state.value.targetProtein,
                                targetCarbohydrates = state.value.targetCarbohydrates
                            ),
                            consumedCalories = totals.totalCalories.toInt(),
                            calorieProgress = (totals.totalCalories.toFloat()/targetCalories),
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun deleteLogEntry(entry: LogEntryDisplay) {
        viewModelScope.launch {
            try {
                setState { copy(isLoading = true, errorMessage = null) }
                logRepository.deleteLog(entry)
                showToast("Deleted ${entry.description} from logs")
            } catch (e: Exception) {
                setState { copy(errorMessage = e.message) }
            } finally {
                setState { copy(isLoading = false) }
            }
        }
    }

    private fun changeDate(transform: LocalDate.() -> LocalDate) {
        val newDate = selectedDate.value.transform()
        setState { copy(selectedDate = newDate, isLoading = true) }
        selectedDate.value = newDate
    }
}