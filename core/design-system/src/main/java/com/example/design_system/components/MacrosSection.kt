package com.example.design_system.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun MacrosSection(
    carbohydrates: Double?,
    protein: Double?,
    fat: Double?,
    fiber: Double?,
    sugar: Double?,
    sodium: Double?
) {
    SectionContainer(title = "Nutritional Macros") {
        Column {
            MacroRow("Carbohydrates", carbohydrates, "g")
            MacroRow("Protein", protein, "g")
            MacroRow("Fat", fat, "g")
            MacroRow("Fiber", fiber, "g")
            MacroRow("Sugar", sugar, "g")
            MacroRow("Sodium", sodium, "mg")
        }
    }
}