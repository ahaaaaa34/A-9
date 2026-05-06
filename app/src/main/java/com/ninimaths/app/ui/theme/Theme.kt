package com.ninimaths.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = TealPrimary,
    onPrimary = Color.White,
    primaryContainer = TealPrimary,
    onPrimaryContainer = Color.White,
    secondary = TealDark,
    onSecondary = Color.White,
    background = BackgroundGray,
    surface = CardWhite,
    onBackground = TextDark,
    onSurface = TextDark,
)

@Composable
fun NinimathsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
