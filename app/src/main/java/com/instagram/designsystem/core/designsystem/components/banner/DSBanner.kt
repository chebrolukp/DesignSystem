package com.instagram.designsystem.core.designsystem.components.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.instagram.designsystem.core.designsystem.components.text.DSText
import com.instagram.designsystem.core.designsystem.components.text.TextVariation
import com.instagram.designsystem.core.designsystem.foundation.theme.DesignSystemSpacing

/**
 * Defines the different visual styles for the [DSBanner] component.
 */
enum class BannerStyle {
    Error,
    Info,
    Success
}

/**
 * A reusable banner component for displaying global messages or alerts.
 *
 * @param message The text to be displayed in the banner.
 * @param style The visual style of the banner (Error, Info, or Success).
 * @param modifier The [Modifier] to be applied to the banner.
 */
@Composable
fun DSBanner(
    message: String,
    modifier: Modifier = Modifier,
    style: BannerStyle = BannerStyle.Error
) {
    val backgroundColor = when (style) {
        BannerStyle.Error -> MaterialTheme.colorScheme.errorContainer
        BannerStyle.Info -> MaterialTheme.colorScheme.secondaryContainer
        BannerStyle.Success -> MaterialTheme.colorScheme.primaryContainer // Simplified mapping
    }

    val contentColor = when (style) {
        BannerStyle.Error -> MaterialTheme.colorScheme.error
        BannerStyle.Info -> MaterialTheme.colorScheme.onSecondaryContainer
        BannerStyle.Success -> MaterialTheme.colorScheme.onPrimaryContainer
    }

    DSText(
        text = message,
        variation = TextVariation.Body,
        color = contentColor,
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(DesignSystemSpacing.Small),
        fontWeight = FontWeight.Bold
    )
}
