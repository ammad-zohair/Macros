package com.example.log_impl.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ammad.shared.extensions.shake
import com.ammad.shared.shake.ShakeConfig
import com.ammad.shared.shake.rememberShakeController
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
    val shakeController = rememberShakeController()

    LaunchedEffect(isEditMode) {
        if (isEditMode) {
            shakeController.shake(ShakeConfig(20, translateX = 2f))
        }
    }
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shake(shakeController),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (macros.isNotEmpty()) {
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
}