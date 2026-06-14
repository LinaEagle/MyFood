package com.mymeals.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightGreen = Color(0xFF2E7D32)
private val DarkGreen = Color(0xFF1B5E20)
private val LightGreenContainer = Color(0xFFA5D6A7)
private val VeryLightGreen = Color(0xFFF1F8E9)
private val Amber = Color(0xFFFF8F00)
private val LightAmber = Color(0xFFFFECB3)
private val DarkBrown = Color(0xFF3E2723)

private val LightColorScheme = lightColorScheme(
    primary = LightGreen,
    onPrimary = Color.White,
    primaryContainer = LightGreenContainer,
    onPrimaryContainer = DarkGreen,
    secondary = Amber,
    onSecondary = Color.White,
    secondaryContainer = LightAmber,
    onSecondaryContainer = DarkBrown,
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    surface = Color.White,
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = VeryLightGreen,
    onSurfaceVariant = Color(0xFF44483E),
    error = Color(0xFFD32F2F),
    onError = Color.White,
    outline = Color(0xFF74796D),
    outlineVariant = Color(0xFFC4C9BB),
)

private val DarkGreenLight = Color(0xFF81C784)
private val DarkAmber = Color(0xFFFFB74D)
private val DarkAmberContainer = Color(0xFF5D4037)
private val DarkSurface = Color(0xFF1E1E1E)
private val DarkBackground = Color(0xFF121212)

private val DarkColorScheme = darkColorScheme(
    primary = DarkGreenLight,
    onPrimary = Color(0xFF003910),
    primaryContainer = LightGreen,
    onPrimaryContainer = LightGreenContainer,
    secondary = DarkAmber,
    onSecondary = DarkBrown,
    secondaryContainer = DarkAmberContainer,
    onSecondaryContainer = LightAmber,
    background = DarkBackground,
    onBackground = Color(0xFFE6E1E5),
    surface = DarkSurface,
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF44483E),
    onSurfaceVariant = Color(0xFFC4C9BB),
    error = Color(0xFFEF5350),
    onError = Color(0xFF690005),
    outline = Color(0xFF8E9386),
    outlineVariant = Color(0xFF44483E),
)

@Composable
fun MyFoodTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(colorScheme = colorScheme, content = content)
}
