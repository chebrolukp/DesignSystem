package com.instagram.designsystem.core.designsystem.foundation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class DesignSystemColors(
    val snackbarAction: Color = DeepSkyBlue,
    val basicSnackbarText: Color = White
)

val LocalDesignSystemColors = staticCompositionLocalOf { DesignSystemColors() }
