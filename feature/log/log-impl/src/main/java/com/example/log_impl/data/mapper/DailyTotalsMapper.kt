package com.example.log_impl.data.mapper

import com.example.database.model.DailyTotals
import com.example.log_api.domain.model.MacroDisplay
import java.util.Locale

fun DailyTotals.toMacroDisplayList(): List<MacroDisplay> {
    return listOf(
        MacroDisplay(
            label = "Protein",
            value = String.format(Locale.US, "%.1fg", totalProtein),
            progress = 0.00f
        ),
        MacroDisplay(
            label = "Carbohydrates",
            value = String.format(Locale.US, "%.1fg", totalCarbohydrates),
            progress = 0.00f
        )
    )
}