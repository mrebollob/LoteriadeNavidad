package com.mrebollob.loteria.android.presentation.platform.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = PrimaryColor,
    primaryVariant = Grey1,
    secondary = SecondaryColor,
    secondaryVariant = LightSecondaryColor,
    background = BackgroundLight,
    surface = SurfaceLight,
    error = SystemRed,
    onPrimary = AccentColor,
    onSecondary = AccentColor,
    onBackground = OnSurfaceLight,
    onSurface = OnSurfaceLight,
    onError = AccentColor
)

private val DarkThemeColors = darkColors(
    primary = PrimaryColor,
    primaryVariant = Grey5,
    secondary = SecondaryColor,
    secondaryVariant = LightSecondaryColor,
    background = BackgroundDark,
    surface = SurfaceDark,
    error = SystemRed,
    onPrimary = AccentColor,
    onSecondary = AccentColor,
    onBackground = OnSurfaceDark,
    onSurface = OnSurfaceDark,
    onError = AccentColor
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
