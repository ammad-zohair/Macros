package com.example.log_impl.data.mapper

import com.example.database.model.DailyTotals
import com.example.log_api.domain.model.MacroDisplay
import java.util.Locale

fun DailyTotals.toMacroDisplayList(
    targetProtein: Int,
    targetCarbohydrates: Int
): List<MacroDisplay> {
    return listOf(
        MacroDisplay(
            label = "Protein",
            value = String.format(Locale.US, "%.1fg", totalProtein),
            progress = totalProtein.toFloat()/targetProtein
        ),
        MacroDisplay(
            label = "Carbohydrates",
            value = String.format(Locale.US, "%.1fg", totalCarbohydrates),
            progress = totalCarbohydrates.toFloat()/targetCarbohydrates
        )
    )
}