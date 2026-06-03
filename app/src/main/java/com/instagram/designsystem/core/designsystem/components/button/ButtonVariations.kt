package com.instagram.designsystem.core.designsystem.components.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ButtonStyle {
    data object Text : ButtonStyle()
    data class Round(
        val imageVector: ImageVector = Icons.AutoMirrored.Filled.ArrowBack
    ) : ButtonStyle()
}

sealed class ButtonVariation {
    data object Primary : ButtonVariation()
    data object Secondary : ButtonVariation()
}