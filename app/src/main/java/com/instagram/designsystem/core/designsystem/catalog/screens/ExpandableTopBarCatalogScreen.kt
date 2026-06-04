package com.instagram.designsystem.core.designsystem.catalog.screens

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.instagram.designsystem.R
import com.instagram.designsystem.core.designsystem.components.topBar.DSExpandableTopBar

@Composable
fun ExpandableTopBarCatalogScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberLazyListState()

    // Max and min heights for calculating the collapse fraction
    val maxHeight = dimensionResource(R.dimen.topbar_max_height)
    val minHeight = dimensionResource(R.dimen.topbar_min_height)
    val maxHeightPx = with(LocalDensity.current) { maxHeight.toPx() }
    val minHeightPx = with(LocalDensity.current) { minHeight.toPx() }

    // Calculate the collapse fraction (0.0 to 1.0) based on scroll position
    val collapseFraction by remember {
        derivedStateOf {
            if (scrollState.firstVisibleItemIndex > 0) {
                1f
            } else {
                val scrollOffset = scrollState.firstVisibleItemScrollOffset.toFloat()
                (scrollOffset / (maxHeightPx - minHeightPx)).coerceIn(0f, 1f)
            }
        }
    }

    Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        // Scrollable Content
        LazyColumn(
            state = scrollState,
            modifier = Modifier.fillMaxSize()
        ) {
            // Spacer to account for the top bar height
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

        // Reusable Expandable Top Bar
        DSExpandableTopBar(
            title = stringResource(R.string.expandable_title),
            collapseFraction = collapseFraction
        )
    }
}
