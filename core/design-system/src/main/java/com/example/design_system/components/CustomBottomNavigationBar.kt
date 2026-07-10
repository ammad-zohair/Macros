package com.example.design_system.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomNavigationBar(
    selectedItem: BottomNavItem?,
    onItemSelected: (BottomNavItem) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerLow,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 40.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BottomNavItem.entries.forEach { item ->
            BottomNavItemView(
                item = item,
                isSelected = item == selectedItem,
                onClick = { onItemSelected(item) },
            )
        }
    }
}

@Composable
private fun BottomNavItemView(
    item: BottomNavItem,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.secondaryContainer else Color.Transparent,
        animationSpec = tween(durationMillis = 200),
        label = "navItemBackground",
    )
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
        animationSpec = tween(durationMillis = 200),
        label = "navItemContent",
    )
    val clickableModifier = Modifier.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick,
    )

    if (isSelected) {
        Surface(
            shadowElevation = 4.dp,
            tonalElevation = 4.dp,
            shape = RoundedCornerShape(24.dp)
        ) {
            Column(
                modifier = clickableModifier
                    .background(backgroundColor)
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.label,
                    tint = contentColor,
                    modifier = Modifier.size(22.dp),
                )
                Text(
                    text = item.label,
                    color = contentColor,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    } else {
        Column(
            modifier = clickableModifier.padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label,
                tint = contentColor,
                modifier = Modifier.size(24.dp),
            )
            Text(
                text = item.label,
                color = contentColor,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}