package com.example.design_system.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FoodHeader(
    description: String,
    calories: Double,
    servingSize: String,
    servingSizeUnit: String,
    isFavorite: Boolean,
    onFavoriteToggle: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = description,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = { onFavoriteToggle() }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = if (isFavorite) "Remove from favourites" else "Add to favourites",
                    tint = if (isFavorite) MaterialTheme.colorScheme.error
                    else MaterialTheme.colorScheme.primary
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            StatBlock(
                label = "Total Calories",
                value = "$calories kcal",
                valueColor = MaterialTheme.colorScheme.primary,
                suffix = " / serving"
            )
            VerticalDivider(
                modifier = Modifier.height(40.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )
            StatBlock(
                label = "Total Serving",
                value = servingSize + servingSizeUnit,
                valueColor = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}