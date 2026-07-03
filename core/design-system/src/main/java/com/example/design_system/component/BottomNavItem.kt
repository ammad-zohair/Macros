package com.example.design_system.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(val label: String, val icon: ImageVector) {
    SEARCH(label = "Search", icon = Icons.Default.Search),
    FAVORITES(label = "Favorites", icon = Icons.Default.Favorite),
    LOG(label = "Log", icon = Icons.Default.DateRange),
}