package com.example.log_impl.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ammad.shared.extensions.shake
import com.ammad.shared.extensions.thenIf
import com.ammad.shared.shake.ShakeConfig
import com.ammad.shared.shake.rememberShakeController
import com.example.design_system.R
import com.example.log_api.domain.model.LogEntryDisplay

@Composable
fun FoodLogItem(
    isEditMode: Boolean,
    entry: LogEntryDisplay,
    onDeleteClick: (LogEntryDisplay) -> Unit,
    modifier: Modifier = Modifier
) {
    val shakeController = rememberShakeController()

    LaunchedEffect(isEditMode) {
        if (isEditMode) {
            shakeController.shake(ShakeConfig(20, translateX = 2f))
        }
    }

    BadgedBox(
        badge = {
            if (isEditMode) {
                Icon(
                    modifier = Modifier
                        .offset(x = (10).dp, y = (-12).dp)
                        .clickable(onClick = { onDeleteClick(entry) } )
                        .padding(2.dp),
                    imageVector = Icons.Default.Delete,
                    tint = MaterialTheme.colorScheme.error,
                    contentDescription = null
                )
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainerLowest, RoundedCornerShape(12.dp))
            .thenIf(!isEditMode) {
                border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(12.dp))
            }
            .shake(shakeController)
            .padding(16.dp),
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_utensils),
                contentDescription = null,
                //tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = entry.description,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = entry.formattedLoggedAt,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${entry.calories}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "kcal",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}