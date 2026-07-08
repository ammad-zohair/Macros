package com.ammad.dashboard_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.design_system.components.SectionContainer

@Composable
fun IngredientsSection(ingredients: List<String>) {
    SectionContainer(title = "Ingredients") {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ingredients.forEach { IngredientChip(it) }
        }
    }
}