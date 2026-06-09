package com.instagram.designsystem.core.designsystem.components.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Defines the different visual styles for the [DSButton] component.
 */
sealed class ButtonStyle {
    /**
     * A standard button with text content.
     */
    data object Text : ButtonStyle()

    /**
     * A round button containing an icon.
     */
    data class Round(
        val imageVector: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
        val contentDescription: String = "Back"
    ) : ButtonStyle()

    /**
     * A ghost icon-only button, typically used in TopBars.
     */
    data class IconOnly(
        val imageVector: ImageVector,
        val contentDescription: String
    ) : ButtonStyle()
}

/**
 * Defines the color variations for the [DSButton] component.
 */
sealed class ButtonVariation {
    data object Primary : ButtonVariation()
    data object Secondary : ButtonVariation()
    data object Ghost : ButtonVariation()
}
