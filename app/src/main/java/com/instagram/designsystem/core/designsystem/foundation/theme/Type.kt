package com.instagram.designsystem.core.designsystem.foundation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = DesignSystemTypographyTokens.FontSizeHeadlineLarge
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = DesignSystemTypographyTokens.FontSizeHeadlineMedium
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = DesignSystemTypographyTokens.FontSizeBodyLarge,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = DesignSystemTypographyTokens.FontSizeBodyMedium
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = DesignSystemTypographyTokens.FontSizeBodySmall
    )
)

object DsTextStyles {
    @Composable
    fun HeadlineMedium() = MaterialTheme.typography.headlineMedium

    @Composable
    fun HeadlineMediumSmall() = MaterialTheme.typography.headlineMedium.copy(
            fontSize = 20.sp
        )
}
