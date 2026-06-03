package com.instagram.designsystem.core.designsystem.components.button

import androidx.compose.ui.graphics.vector.ImageVector

sealed class ButtonStyle {
    data object Text : ButtonStyle()
    data class Round(
        val imageVector: ImageVector,
        val contentDescription: String
    ) : ButtonStyle()
}

sealed class ButtonVariation {
    data object Primary : ButtonVariation()
    data object Secondary : ButtonVariation()
}