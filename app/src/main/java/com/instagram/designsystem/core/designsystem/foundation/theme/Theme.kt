package com.instagram.designsystem.core.designsystem.foundation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = DeepSkyBlue,
    tertiary = Pink80,
    background = SurfaceDark,
    surface = SurfaceDark,
    inverseSurface = White,
    inverseOnSurface = Black,
    surfaceContainer = SurfaceDark
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = DeepSkyBlue,
    tertiary = Pink40,
    background = SurfaceLight,
    surface = SurfaceLight,
    inverseSurface = Black,
    inverseOnSurface = White,
    surfaceContainer = LightGray
)

@Composable
fun DesignSystemTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}
