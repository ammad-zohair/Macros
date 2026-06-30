package com.ammad.dashboard_impl.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ammad.dashboard_impl.domain.model.FoodItem
import com.example.design_system.component.CustomButton

@Composable
fun FoodDetailCard(
    foodItem: FoodItem,
    isFavorite: Boolean,
    onFavoriteToggle: () -> Unit,
    onAddToLog: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            FoodHeader(foodItem, isFavorite, onFavoriteToggle)
            IngredientsSection(foodItem.ingredients.split(",").map { it.trim() })
            MacrosSection(foodItem.labelNutrients)
            CustomButton(
                onClick = onAddToLog,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                icon = Icons.Default.Add,
                buttonText = "Add to Daily Log"
            )
        }
    }
}