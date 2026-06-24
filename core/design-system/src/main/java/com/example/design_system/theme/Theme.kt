package com.example.design_system.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.design_system.theme.color.BackgroundColor
import com.example.design_system.theme.color.ErrorColor
import com.example.design_system.theme.color.ErrorContainer
import com.example.design_system.theme.color.InverseOnSurface
import com.example.design_system.theme.color.InversePrimary
import com.example.design_system.theme.color.InverseSurface
import com.example.design_system.theme.color.OnBackground
import com.example.design_system.theme.color.OnError
import com.example.design_system.theme.color.OnErrorContainer
import com.example.design_system.theme.color.OnPrimary
import com.example.design_system.theme.color.OnPrimaryContainer
import com.example.design_system.theme.color.OnSecondary
import com.example.design_system.theme.color.OnSecondaryContainer
import com.example.design_system.theme.color.OnSurface
import com.example.design_system.theme.color.OnSurfaceVariant
import com.example.design_system.theme.color.OnTertiary
import com.example.design_system.theme.color.OnTertiaryContainer
import com.example.design_system.theme.color.Outline
import com.example.design_system.theme.color.OutlineVariant
import com.example.design_system.theme.color.Primary
import com.example.design_system.theme.color.PrimaryContainer
import com.example.design_system.theme.color.Secondary
import com.example.design_system.theme.color.SecondaryContainer
import com.example.design_system.theme.color.SurfaceBright
import com.example.design_system.theme.color.SurfaceColor
import com.example.design_system.theme.color.SurfaceContainer
import com.example.design_system.theme.color.SurfaceContainerHigh
import com.example.design_system.theme.color.SurfaceContainerHighest
import com.example.design_system.theme.color.SurfaceContainerLow
import com.example.design_system.theme.color.SurfaceContainerLowest
import com.example.design_system.theme.color.SurfaceDim
import com.example.design_system.theme.color.SurfaceVariant
import com.example.design_system.theme.color.Tertiary
import com.example.design_system.theme.color.TertiaryContainer
import com.example.design_system.theme.typography.AppTypography

private val LightColors = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    primaryContainer = PrimaryContainer,
    onPrimaryContainer = OnPrimaryContainer,
    secondary = Secondary,
    onSecondary = OnSecondary,
    secondaryContainer = SecondaryContainer,
    onSecondaryContainer = OnSecondaryContainer,
    tertiary = Tertiary,
    onTertiary = OnTertiary,
    tertiaryContainer = TertiaryContainer,
    onTertiaryContainer = OnTertiaryContainer,
    error = ErrorColor,
    onError = OnError,
    errorContainer = ErrorContainer,
    onErrorContainer = OnErrorContainer,
    background = BackgroundColor,
    onBackground = OnBackground,
    surface = SurfaceColor,
    onSurface = OnSurface,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = OnSurfaceVariant,
    outline = Outline,
    outlineVariant = OutlineVariant,
    surfaceContainerLowest = SurfaceContainerLowest,
    surfaceContainerLow = SurfaceContainerLow,
    surfaceContainer = SurfaceContainer,
    surfaceContainerHigh = SurfaceContainerHigh,
    surfaceContainerHighest = SurfaceContainerHighest,
    surfaceDim = SurfaceDim,
    surfaceBright = SurfaceBright,
    inverseSurface = InverseSurface,
    inverseOnSurface = InverseOnSurface,
    inversePrimary = InversePrimary
)

@Composable
fun MacrosTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content
    )
}
