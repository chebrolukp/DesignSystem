package com.instagram.designsystem.core.designsystem.foundation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80, //prominent color of app. Used on buttons, key UI elements as background
    secondary = DeepSkyBlue,//support accent, secondary buttons, less prominent interactive elements.
    tertiary = Pink80,//optional 3rd accent. contrast highlights, special UI moments, badges.
    background = SurfaceDark, // base color behind all screens
    surface = SurfaceDark,//color of cards, sheets, dialogs, elevated content, container where text would be
    inverseSurface = White,//snackbar or tooltips background - intentionally flips surface color for contrast
    inverseOnSurface = Black,//text color on inverse surface
    surfaceContainer = SurfaceDark //refined surface
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

    val customColors = DesignSystemColors(
        snackbarAction = DeepSkyBlue,
        basicSnackbarText = if (darkTheme) Black else White
    )

    CompositionLocalProvider(LocalDesignSystemColors provides customColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            content = content
        )
    }
}
