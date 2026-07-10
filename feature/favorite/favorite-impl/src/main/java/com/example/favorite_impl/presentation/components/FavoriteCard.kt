package com.example.favorite_impl.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.design_system.components.FoodHeader
import com.example.design_system.components.MacrosSection
import com.example.favorite_api.domain.model.Favorite

@Composable
fun FavoriteCard(
    favoriteItem: Favorite,
    onFavoriteToggle: (Favorite) -> Unit,
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
            FoodHeader(
                description = favoriteItem.description,
                calories = favoriteItem.calories,
                servingSize = favoriteItem.servingSize,
                servingSizeUnit = favoriteItem.servingSizeUnit,
                isFavorite = true,
                onFavoriteToggle = { onFavoriteToggle(favoriteItem) }
            )
            MacrosSection(
                carbohydrates = favoriteItem.carbohydrates,
                protein = favoriteItem.protein,
                fat = favoriteItem.fat,
                fiber = 0.0,
                sugar = favoriteItem.sugar,
                sodium = favoriteItem.sodium
            )
        }
    }
}