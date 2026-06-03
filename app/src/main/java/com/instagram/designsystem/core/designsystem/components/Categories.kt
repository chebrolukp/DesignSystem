package com.instagram.designsystem.core.designsystem.components

import androidx.annotation.StringRes
import com.instagram.designsystem.R
import com.instagram.designsystem.core.designsystem.navigation.CatalogRoute

data class ComponentCategory(
    @StringRes val titleRes: Int,
    val route: String
)

val categories = listOf(
    ComponentCategory(
        titleRes = R.string.category_buttons,
        route = CatalogRoute.Buttons.route
    ),
    ComponentCategory(
        titleRes = R.string.category_top_bars,
        route = CatalogRoute.TopBars.route
    ),
    ComponentCategory(
        titleRes = R.string.category_expandable_topbar,
        route = CatalogRoute.ExpandableTopBar.route
    ),
    ComponentCategory(
        titleRes = R.string.category_bottom_sheets,
        route = CatalogRoute.BottomSheets.route
    ),
    ComponentCategory(
        titleRes = R.string.category_snackbars,
        route = CatalogRoute.Snackbars.route
    )
)