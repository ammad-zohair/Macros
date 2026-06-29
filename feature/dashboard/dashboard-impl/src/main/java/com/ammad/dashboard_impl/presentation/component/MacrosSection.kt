package com.ammad.dashboard_impl.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.ammad.dashboard_impl.data.remote.dto.LabelNutrients

@Composable
fun MacrosSection(macros: LabelNutrients) {
    SectionContainer(title = "Nutritional Macros") {
        Column {
            MacroRow("Carbohydrates", macros.carbohydrates?.value)
            MacroRow("Protein", macros.protein?.value)
            MacroRow("Fat", macros.fat?.value)
            MacroRow("Fiber", macros.fiber?.value)
            MacroRow("Sugar", macros.sugars?.value)
            MacroRow("Sodium", macros.sodium?.value)
        }
    }
}