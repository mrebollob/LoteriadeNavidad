package com.mrebollob.loteria.android.presentation.platform.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = Grey8,
    primaryVariant = Color.Black,
    secondary = Grey2,
    secondaryVariant = Grey3,
    background = BackgroundLight,
    surface = BackgroundLight,
    error = SystemRed,
    onPrimary = Color.White,
    onSecondary = Grey7,
    onBackground = OnSurfaceLight,
    onSurface = OnSurfaceLight,
    onError = Grey7
)

private val DarkThemeColors = darkColors(
    primary = Grey3,
    primaryVariant = Color.Black,
    secondary = Grey4,
    secondaryVariant = Grey5,
    background = BackgroundDark,
    surface = BackgroundDark,
    error = SystemRed,
    onPrimary = Grey8,
    onSecondary = Color.White,
    onBackground = OnSurfaceDark,
    onSurface = OnSurfaceDark,
    onError = Grey7
)

@Composable
fun LotteryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) {
            DarkThemeColors
        } else {
            LightThemeColors
        },
        typography = TabataTypography,
        shapes = LotteryShapes,
        content = content
    )
}
