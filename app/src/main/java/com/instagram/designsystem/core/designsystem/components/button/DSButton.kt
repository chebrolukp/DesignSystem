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
import androidx.compose.ui.unit.dp
import com.instagram.designsystem.core.designsystem.preview.MultiPreview
import com.instagram.designsystem.core.designsystem.theme.Black
import com.instagram.designsystem.core.designsystem.theme.White

@Composable
fun AppButton(
    text: String,
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
            enabled = enabled,
            colors = colors,
            onClick = onClick
        ) {
            Text(text)
        }

        is ButtonStyle.Round -> Button(
            enabled = enabled,
            colors = colors,
            onClick = onClick
        ) {
            Icon(
                imageVector = style.imageVector,
                tint = if(enabled) White else Black,
                contentDescription = text
            )
        }
    }
}

@MultiPreview
@Composable
fun AppButtonPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text("Primary")
        AppButton("Primary Enabled")
        AppButton("Primary Disabled", enabled = false)
        HorizontalDivider()

        Text("Secondary")
        AppButton("Secondary Enabled", variant = ButtonVariation.Secondary)
        AppButton("Secondary Disabled", enabled = false, variant = ButtonVariation.Secondary)
        HorizontalDivider()

        Text("Primary Round Enabled")
        AppButton(text = "Primary Round Enabled", style = ButtonStyle.Round())
        AppButton(text = "Primary Round Enabled", enabled = false, style = ButtonStyle.Round())
        HorizontalDivider()

        Text("Secondary Round Disabled")
        AppButton(text = "Secondary Round Enabled", style = ButtonStyle.Round(), variant = ButtonVariation.Secondary)
        AppButton(text = "Secondary Round Enabled", enabled = false, style = ButtonStyle.Round(), variant = ButtonVariation.Secondary)
    }
}