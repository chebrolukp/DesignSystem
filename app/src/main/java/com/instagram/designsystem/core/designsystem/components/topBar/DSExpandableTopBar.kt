package com.instagram.designsystem.core.designsystem.components.topBar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.instagram.designsystem.R
import com.instagram.designsystem.core.designsystem.foundation.animation.dsExpand
import com.instagram.designsystem.core.designsystem.foundation.theme.DesignSystemSizing
import com.instagram.designsystem.core.designsystem.foundation.theme.DesignSystemSpacing
import com.instagram.designsystem.core.designsystem.foundation.theme.DesignSystemTypographyTokens

/**
 * A top bar that expands and collapses based on a provided [collapseFraction].
 *
 * @param title The title text to be displayed.
 * @param collapseFraction A value between 0.0 (fully expanded) and 1.0 (fully collapsed).
 * @param modifier The modifier to be applied to the top bar.
 * @param maxHeight The height of the top bar when fully expanded.
 * @param minHeight The height of the top bar when fully collapsed.
 * @param maxFontSize The font size of the title when fully expanded.
 * @param minFontSize The font size of the title when fully collapsed.
 */
@Composable
fun DSExpandableTopBar(
    title: String,
    collapseFraction: Float,
    modifier: Modifier = Modifier,
    maxHeight: Dp = DesignSystemSizing.TopBarMaxHeight,
    minHeight: Dp = DesignSystemSizing.TopBarMinHeight,
    maxFontSize: TextUnit = DesignSystemTypographyTokens.FontSizeHeadlineExtraLarge,
    minFontSize: TextUnit = DesignSystemTypographyTokens.FontSizeHeadlineMedium
) {
    val expandedDescription = stringResource(R.string.expanded)
    val collapsedDescription = stringResource(R.string.collapsed)

    val animatedHeight by animateDpAsState(
        targetValue = lerp(maxHeight, minHeight, collapseFraction),
        animationSpec = dsExpand(),
        label = "height"
    )

    val animatedTextSizeValue by animateFloatAsState(
        targetValue = lerp(maxFontSize, minFontSize, collapseFraction).value,
        animationSpec = dsExpand(),
        label = "textSize"
    )

    val animatedBackgroundColor by animateColorAsState(
        targetValue = lerp(
            MaterialTheme.colorScheme.surfaceContainer,
            MaterialTheme.colorScheme.surfaceContainerHighest,
            collapseFraction
        ),
        label = "color"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(animatedHeight)
            .background(animatedBackgroundColor)
            .padding(horizontal = DesignSystemSpacing.Medium)
            .semantics {
                stateDescription = if (collapseFraction < 0.5f) expandedDescription else collapsedDescription
            },
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = title,
            fontSize = animatedTextSizeValue.sp,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
