package com.example.log_impl.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design_system.components.CustomButton
import com.example.design_system.components.EmptyView
import com.example.log_api.domain.model.Log
import com.example.log_impl.presentation.components.DailyGoalCard
import com.example.log_impl.presentation.components.DateSelector
import com.example.log_impl.presentation.components.FoodLogItem
import com.example.log_impl.presentation.components.MacroBlocks
import com.example.log_api.domain.model.MacroDisplay
import com.example.log_impl.data.mapper.toDateText
import com.example.log_impl.data.mapper.toDayLabel

@Composable
fun LogScreen(
    paddingValues: PaddingValues,
    state: LogState,
    onIntent: (LogIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    LogDetailScreen(
        isEditMode = state.isEdit,
        dailyLogEntries = state.entries,
        macros = state.macros,
        dayLabel = state.selectedDate.toDayLabel(),
        dateText = state.selectedDate.toDateText(),
        consumedCalories = state.consumedCalories,
        targetCalories = state.targetCalories,
        calorieProgress = state.calorieProgress,
        targetCarbohydrates = state.targetCarbohydrates,
        targetProtein = state.targetProtein,
        onPreviousDay = { onIntent(LogIntent.PreviousDay) },
        onNextDay = { onIntent(LogIntent.NextDay) },
        onAddFoodClick = { onIntent(LogIntent.AddFood) },
        onGoBack = { onIntent(LogIntent.GoBack) },
        onProteinSliderChange = { onIntent(LogIntent.ProteinTargetChange(it)) },
        onCarbohydrateSliderChange = { onIntent(LogIntent.CarbohydrateTargetChange(it)) },
        modifier = modifier.padding(paddingValues),
    )
}

@Composable
private fun LogDetailScreen(
    isEditMode: Boolean,
    dailyLogEntries: List<Log>,
    macros: List<MacroDisplay>,
    dayLabel: String?,
    dateText: String,
    consumedCalories: Int,
    targetCalories: Int,
    calorieProgress: Float,
    targetCarbohydrates: Int,
    targetProtein: Int,
    onPreviousDay: () -> Unit,
    onNextDay: () -> Unit,
    onAddFoodClick: () -> Unit,
    onGoBack: () -> Unit,
    onProteinSliderChange: (Float) -> Unit,
    onCarbohydrateSliderChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (dailyLogEntries.isEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmptyView("No entries for $dateText")
                Spacer(Modifier.height(48.dp))
                CustomButton(
                    icon = Icons.Default.Error,
                    buttonText = "Return",
                    onClick = { onGoBack()}
                )
            }
        }
        else {
            DateSelector(
                dayLabel = dayLabel,
                dateText = dateText,
                onPreviousDay = onPreviousDay,
                onNextDay = onNextDay,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            DailyGoalCard(
                consumedCalories = consumedCalories,
                targetCalories = targetCalories,
                progress = calorieProgress
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Nutrient Breakdown",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row(
                    modifier = Modifier
                        .clickable(
                            onClick = {

                            }
                        )
                ) {
                    Icon(
                        imageVector = if (isEditMode) Icons.Default.Done else Icons.Default.Edit,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                }
            }

            MacroBlocks(
                isEditMode = isEditMode,
                macros = macros,
                targetCarbohydrates = targetCarbohydrates,
                targetProtein = targetProtein,
                onProteinSliderChange = onProteinSliderChange,
                onCarbohydrateSliderChange = onCarbohydrateSliderChange
            )

            Spacer(Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Today's Entries",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                CustomButton(
                    onClick = onAddFoodClick,
                    buttonText = "Add Food"
                )
            }

            dailyLogEntries.forEach { entry ->
                FoodLogItem(entry = entry)
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}