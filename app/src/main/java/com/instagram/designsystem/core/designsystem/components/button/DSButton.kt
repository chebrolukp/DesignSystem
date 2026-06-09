package com.instagram.designsystem.core.designsystem.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.instagram.designsystem.core.designsystem.foundation.theme.DesignSystemSpacing
import com.instagram.designsystem.core.designsystem.preview.MultiPreview

/**
 * A reusable button component that supports different styles and variations.
 *
 * @param text The text to be displayed on the button (or used for accessibility in [ButtonStyle.Round]).
 * @param enabled Controls the enabled state of the button. When `false`, this component will not
 * respond to user input, and it will appear visually disabled.
 * @param style The visual style of the button. Can be [ButtonStyle.Text] or [ButtonStyle.Round].
 * @param variant The color variation of the button. Can be [ButtonVariation.Primary] or [ButtonVariation.Secondary].
 * @param onClick Called when this button is clicked.
 */
@Composable
fun DSButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    style: ButtonStyle = ButtonStyle.Text,
    variant: ButtonVariation = ButtonVariation.Primary,
    onClick: () -> Unit = {}
) {
    val colors = when (variant) {
        ButtonVariation.Primary -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )

        ButtonVariation.Secondary -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    }
    when (style) {
        ButtonStyle.Text -> Button(
            modifier = modifier,
            enabled = enabled,
            colors = colors,
            onClick = onClick
        ) {
            Text(text)
        }

        is ButtonStyle.Round -> Button(
            modifier = modifier,
            enabled = enabled,
            colors = colors,
            onClick = onClick
        ) {
            Icon(
                imageVector = style.imageVector,
                tint = if (enabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                contentDescription = style.contentDescription
            )
        }
    }
}

@MultiPreview
@Composable
fun DSButtonPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(DesignSystemSpacing.Small)) {
        Text("Primary")
        DSButton("Primary Enabled")
        DSButton("Primary Disabled", enabled = false)
        HorizontalDivider()

        Text("Secondary")
        DSButton("Secondary Enabled", variant = ButtonVariation.Secondary)
        DSButton("Secondary Disabled", enabled = false, variant = ButtonVariation.Secondary)
        HorizontalDivider()

        Text("Primary Round Enabled")
        DSButton(
            text = "Back",
            style = ButtonStyle.Round()
        )
        DSButton(
            text = "Back",
            enabled = false,
            style = ButtonStyle.Round()
        )
        HorizontalDivider()

        Text("Secondary Round Disabled")
        DSButton(
            text = "Back",
            style = ButtonStyle.Round(),
            variant = ButtonVariation.Secondary
        )
        DSButton(
            text = "Back",
            enabled = false,
            style = ButtonStyle.Round(),
            variant = ButtonVariation.Secondary
        )
    }
}