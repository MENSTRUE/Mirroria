package com.mirrora.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// MIRRORA is a light-only, calm design system (see design spec section 5).
private val MirroraColorScheme = lightColorScheme(
    primary = MirroraPrimaryBlue,
    onPrimary = Color.White,
    primaryContainer = MirroraBlueLight,
    onPrimaryContainer = MirroraTextPrimary,
    background = MirroraBackground,
    onBackground = MirroraTextPrimary,
    surface = MirroraSurface,
    onSurface = MirroraTextPrimary,
    surfaceVariant = MirroraMutedBackground,
    onSurfaceVariant = MirroraTextSecondary,
    outline = MirroraBorder,
    error = MirroraDanger,
    onError = Color.White
)

@Composable
fun MirroraTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MirroraColorScheme,
        typography = MirroraTypography,
        shapes = MirroraShapes,
        content = content
    )
}
