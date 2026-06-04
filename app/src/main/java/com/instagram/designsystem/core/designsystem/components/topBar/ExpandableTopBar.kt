package com.instagram.designsystem.core.designsystem.components.topBar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.instagram.designsystem.R
import com.instagram.designsystem.core.designsystem.components.animation.dsExpand

@Composable
fun ExpandableTopBarScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()

    // ... dimen calculations ...
    val maxHeight = dimensionResource(R.dimen.topbar_max_height)
    val minHeight = dimensionResource(R.dimen.topbar_min_height)
    val maxHeightPx = with(LocalDensity.current) { maxHeight.toPx() }
    val minHeightPx = with(LocalDensity.current) { minHeight.toPx() }

    // Calculate the collapse fraction (0.0 to 1.0)
    val collapseFraction by remember {
        derivedStateOf {
            if (scrollState.firstVisibleItemIndex > 0) {
                1f
            } else {
                val scrollOffset = scrollState.firstVisibleItemScrollOffset.toFloat()
                // Collapse faster or slower by adjusting this divisor
                (scrollOffset / (maxHeightPx - minHeightPx)).coerceIn(0f, 1f)
            }
        }
    }

    // Smoothly animate the transitions
    val animatedHeight by animateDpAsState(
        targetValue = lerp(maxHeight, minHeight, collapseFraction),
        animationSpec = dsExpand(),
        label = "height"
    )

    val animatedTextSizeValue by animateFloatAsState(
        targetValue = lerp(34.sp, 18.sp, collapseFraction).value,
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

    Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        // Content
        LazyColumn(
            state = scrollState,
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Add a spacer to push content below the top bar initially
            item {
                Box(modifier = Modifier.height(maxHeight))
            }
            items(100) { index ->
                Text(
                    text = "Item $index",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_medium)),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        // Expandable Top Bar
        val expandedDescription = stringResource(R.string.expanded)
        val collapsedDescription = stringResource(R.string.collapsed)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(animatedHeight)
                .background(animatedBackgroundColor)
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
                .semantics {
                    stateDescription = if (collapseFraction < 0.5f) expandedDescription else collapsedDescription
                },
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = stringResource(R.string.expandable_title),
                fontSize = animatedTextSizeValue.sp,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
