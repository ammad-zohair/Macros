package com.example.log_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.log_api.domain.model.MacroDisplay

@Composable
fun MacroBlocks(
    isEditMode: Boolean,
    macros: List<MacroDisplay>,
    targetCarbohydrates: Int,
    targetProtein: Int,
    onProteinSliderChange: (Float) -> Unit,
    onCarbohydrateSliderChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MacroCard(
            isEditMode = isEditMode,
            macro = macros[0],
            targetMacro = targetProtein,
            onMacroSliderChange = onProteinSliderChange,
            modifier = Modifier.weight(1f)
        )
        MacroCard(
            isEditMode = isEditMode,
            macro = macros[1],
            targetMacro = targetCarbohydrates,
            onMacroSliderChange = onCarbohydrateSliderChange,
            modifier = Modifier.weight(1f)
        )
    }
}