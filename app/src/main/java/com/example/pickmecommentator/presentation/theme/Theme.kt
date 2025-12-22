package com.example.pickmecommentator.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = RobotBlue,
    onPrimary = LightText,
    secondary = LightRobotBlue,
    background = DarkBackground,
    surface = DarkSurface, // Slightly lighter than background
    surfaceVariant = DarkSurfaceVariant, // For cards
    onBackground = LightText,
    onSurface = LightText,
    onSurfaceVariant = LightText
)

private val LightColorScheme = lightColorScheme(
    primary = RobotBlue,
    onPrimary = LightText,
    secondary = LightRobotBlue,
    background = LightBackground,
    surface = WhiteSurface, // For list background
    surfaceVariant = WhiteSurface, // For cards
    onBackground = DarkText,
    onSurface = DarkText,
    onSurfaceVariant = DarkText
)

@Composable
fun PickMeCommentatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
