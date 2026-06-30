package com.ammad.dashboard_impl.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.ammad.dashboard_impl.data.remote.dto.LabelNutrients

@Composable
fun MacrosSection(macros: LabelNutrients) {
    SectionContainer(title = "Nutritional Macros") {
        Column {
            MacroRow("Carbohydrates", macros.carbohydrates?.value, "g")
            MacroRow("Protein", macros.protein?.value, "g")
            MacroRow("Fat", macros.fat?.value, "g")
            MacroRow("Fiber", macros.fiber?.value, "g")
            MacroRow("Sugar", macros.sugars?.value, "g")
            MacroRow("Sodium", macros.sodium?.value, "mg")
        }
    }
}