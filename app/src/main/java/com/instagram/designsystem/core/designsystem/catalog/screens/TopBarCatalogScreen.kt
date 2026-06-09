package com.instagram.designsystem.core.designsystem.catalog.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.instagram.designsystem.core.designsystem.components.topBar.DSTopBar
import com.instagram.designsystem.core.designsystem.components.topBar.TopBarVariant
import com.instagram.designsystem.core.designsystem.foundation.theme.DesignSystemSpacing

/**
 * The TopAppBar in Material 3 includes default window insets (specifically for the status bar) which adds extra padding at the top.
 * When you use it inside a LazyColumn or anywhere other than the very top of a Scaffold, it will still reserve that space, making it look much taller than expected.
 */
@Composable
fun TopBarCatalogScreen(modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        item {
            Spacer(modifier = Modifier.height(DesignSystemSpacing.Small))
        }
        item {
            Surface {
                DSTopBar(
                    variant = TopBarVariant.Simple(
                        title = "Simple"
                    ),
                    windowInsets = WindowInsets(0, 0, 0, 0)
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(DesignSystemSpacing.Small))
        }
        item {
            DSTopBar(
                variant = TopBarVariant.Back(
                    title = "Back",
                    onBackClick = {}
                ),
                windowInsets = WindowInsets(0, 0, 0, 0)
            )
        }
        item {
            Spacer(modifier = Modifier.height(DesignSystemSpacing.Small))
        }
        item {
            DSTopBar(
                variant = TopBarVariant.Action(
                    title = "Action",
                    onActionClick = {}
                ),
                windowInsets = WindowInsets(0, 0, 0, 0)
            )
        }
        item {
            Spacer(modifier = Modifier.height(DesignSystemSpacing.Small))
        }
        item {
            DSTopBar(
                variant = TopBarVariant.NavigationAction(
                    title = "Dual Action",
                    onBackClick = {},
                    onActionClick = {}
                ),
                windowInsets = WindowInsets(0, 0, 0, 0)
            )
        }
    }
}
