package com.instagram.designsystem.core.designsystem.catalog.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.instagram.designsystem.core.designsystem.components.categories

@Composable
fun CatalogHomeScreen(
    windowSizeClass: WindowSizeClass,
    modifier: Modifier,
    onItemClick: (String) -> Unit
) {
    val columns = when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> 1
        WindowWidthSizeClass.Medium -> 2
        else -> 3
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        modifier = modifier
    ) {
        items(categories.size) { index ->
            val item = categories[index]
            ListItem(
                headlineContent = {
                    Text(stringResource(item.titleRes))
                },
                modifier = Modifier.clickable {
                    onItemClick(item.route)
                }
            )
        }
    }
}
