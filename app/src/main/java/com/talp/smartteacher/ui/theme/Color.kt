package com.talp.smartteacher.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Color Palette - Green to Beige gradient
private val GreenPrimary = Color(0xFF2E7D32)
private val GreenSecondary = Color(0xFF1B5E20)
private val BeigeTertiary = Color(0xFFF5E6C4)
private val BeigeLight = Color(0xFFFEF5E7)

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = Color.White,
    primaryContainer = BeigeTertiary,
    onPrimaryContainer = GreenPrimary,
    secondary = GreenSecondary,
    onSecondary = Color.White,
    secondaryContainer = BeigeTertiary,
    onSecondaryContainer = GreenSecondary,
    tertiary = BeigeTertiary,
    onTertiary = GreenPrimary,
    tertiaryContainer = BeigeLight,
    onTertiaryContainer = GreenPrimary,
    background = BeigeLight,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color(0xFFB3261E),
    onError = Color.White,
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF8C0605)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF7DCC8A),
    onPrimary = GreenSecondary,
    primaryContainer = GreenPrimary,
    onPrimaryContainer = Color.White,
    secondary = Color(0xFF9DCE7E),
    onSecondary = GreenSecondary,
    secondaryContainer = Color(0xFF37652C),
    onSecondaryContainer = Color(0xFFC6F6D5),
    tertiary = BeigeTertiary,
    onTertiary = Color(0xFF6B5D2F),
    tertiaryContainer = Color(0xFF524814),
    onTertiaryContainer = Color(0xFFFFE5A8),
    background = Color(0xFF1A1A1A),
    onBackground = Color.White,
    surface = Color(0xFF262626),
    onSurface = Color.White,
    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410),
    errorContainer = Color(0xFF8C0605),
    onErrorContainer = Color(0xFFF9DEDC)
)

@Composable
fun TalpSmartTeacherTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = TalpTypography,
        shapes = TalpShapes,
        content = content
    )
}
