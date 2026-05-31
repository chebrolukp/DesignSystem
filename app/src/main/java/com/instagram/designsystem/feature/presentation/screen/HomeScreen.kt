package com.instagram.designsystem.feature.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.instagram.designsystem.core.designsystem.components.categories

@Composable
fun CatalogHomeScreen(
    modifier: Modifier,
    onItemClick: (String) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(categories.size) { index ->
            val item = categories[index]
            ListItem(
                headlineContent = {
                    Text(item.title)
                },
                modifier = Modifier.clickable {
                    onItemClick(item.route)
                }
            )
        }
    }
}