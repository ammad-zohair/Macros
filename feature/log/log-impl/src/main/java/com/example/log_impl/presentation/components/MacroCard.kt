package com.example.log_impl.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.log_api.domain.model.MacroDisplay


@Composable
fun MacroCard(
    macro: MacroDisplay,
    targetMacro: Int,
    onMacroSliderChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var sliderPosition by remember { mutableFloatStateOf(macro.progress) }

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainer, RoundedCornerShape(8.dp))
            .border(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = macro.label.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Spacer(Modifier.height(4.dp))
        Row {
            Text(
                text = macro.value,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = " / $targetMacro g",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Spacer(Modifier.height(8.dp))
        Slider(
            value = macro.progress,
            onValueChange = {
                sliderPosition = it
                onMacroSliderChange(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .clip(RoundedCornerShape(50)),
            enabled = true,
            valueRange = 5f..1000f,
            colors = SliderDefaults.colors(activeTickColor = MaterialTheme.colorScheme.outlineVariant)
        )
//        LinearProgressIndicator(
//            progress = { macro.progress },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(4.dp)
//                .clip(RoundedCornerShape(50)),
//            color = MaterialTheme.colorScheme.secondary,
//            trackColor = MaterialTheme.colorScheme.outlineVariant,
//            strokeCap = StrokeCap.Round
//        )
    }
}