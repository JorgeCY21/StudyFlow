package com.example.studyflow.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = StudyFlowBlue,
    secondary = StudyFlowTeal,
    tertiary = StudyFlowIndigo,
    background = StudyFlowBackground,
    surface = StudyFlowSurface,
    surfaceVariant = StudyFlowSurfaceVariant,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    onSecondary = androidx.compose.ui.graphics.Color.White,
    onTertiary = androidx.compose.ui.graphics.Color.White,
    onBackground = StudyFlowText,
    onSurface = StudyFlowText,
    onSurfaceVariant = StudyFlowMuted,
)

private val DarkColorScheme = darkColorScheme(
    primary = StudyFlowMint,
    secondary = StudyFlowTeal,
    tertiary = StudyFlowIndigo,
    background = androidx.compose.ui.graphics.Color(0xFF0F172A),
    surface = androidx.compose.ui.graphics.Color(0xFF111827),
    surfaceVariant = androidx.compose.ui.graphics.Color(0xFF1F2937),
    onPrimary = androidx.compose.ui.graphics.Color(0xFF0F172A),
    onSecondary = androidx.compose.ui.graphics.Color.White,
    onTertiary = androidx.compose.ui.graphics.Color.White,
    onBackground = androidx.compose.ui.graphics.Color(0xFFF8FAFC),
    onSurface = androidx.compose.ui.graphics.Color(0xFFF8FAFC),
    onSurfaceVariant = androidx.compose.ui.graphics.Color(0xFFCBD5E1),
)

@Composable
fun StudyFlowTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content,
    )
}
