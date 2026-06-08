package com.instagram.designsystem.core.designsystem.components.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import com.instagram.designsystem.core.designsystem.foundation.theme.DesignSystemTypographyTokens

/**
 * Defines the different typography variations for the [DSText] component.
 */
enum class TextVariation {
    Headline,
    Body,
    Error,
    Label
}

/**
 * A reusable text component that applies design system typography and color tokens.
 *
 * @param text The text to be displayed.
 * @param variation The typography variation to apply.
 * @param modifier The [Modifier] to be applied to the text.
 * @param color Optional override for the text color.
 * @param fontWeight Optional override for the font weight.
 */
@Composable
fun DSText(
    text: String,
    variation: TextVariation,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    maxLines: Int = 1,
    fontWeight: FontWeight? = null,
    ellipsize: Boolean = true
) {
    val style: TextStyle = when (variation) {
        TextVariation.Headline -> MaterialTheme.typography.headlineMedium.copy(fontWeight = DesignSystemTypographyTokens.Bold)
        TextVariation.Body -> MaterialTheme.typography.bodyMedium
        TextVariation.Error -> TextStyle(
            fontSize = DesignSystemTypographyTokens.FontSizeBodySmall,
            fontWeight = FontWeight.Normal
        )
        TextVariation.Label -> MaterialTheme.typography.labelMedium
    }

    val textColor = if (color != Color.Unspecified) color else {
        when (variation) {
            TextVariation.Error -> MaterialTheme.colorScheme.error
            else -> MaterialTheme.colorScheme.onSurface
        }
    }

    Text(
        text = text,
        modifier = modifier.semantics {
            if (variation == TextVariation.Headline) {
                heading()
            }
        },
        style = style,
        color = textColor,
        fontWeight = fontWeight ?: style.fontWeight,
        maxLines = maxLines,
        overflow = if(ellipsize) TextOverflow.Ellipsis else TextOverflow.Clip
    )
}
