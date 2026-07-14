package com.example.log_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.log_impl.data.mapper.toDateText
import java.time.LocalDate

@Composable
fun DateSelector(
    dayLabel: String?,
    dateText: String,
    onPreviousDay: () -> Unit,
    onNextDay: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPreviousDay) {
            Icon(
                imageVector = Icons.Default.ChevronLeft,
                contentDescription = "Previous day",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            dayLabel?.let {
                Text(
                    text = it.uppercase(),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = dateText,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        IconButton(
            onClick = onNextDay,
            enabled = dateText != LocalDate.now().toDateText()
        ) {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Next day",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}