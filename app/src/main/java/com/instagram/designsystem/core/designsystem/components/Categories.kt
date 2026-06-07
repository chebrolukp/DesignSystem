package com.instagram.designsystem.core.designsystem.components

import androidx.annotation.StringRes
import com.instagram.designsystem.R
import com.instagram.designsystem.core.designsystem.catalog.navigation.CatalogRoute

/**
 * Represents a category of components in the design system catalog.
 *
 * @property titleRes The resource ID of the string to be displayed as the category title.
 * @property route The navigation route associated with this category.
 */
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
    ),
    ComponentCategory(
        titleRes = R.string.category_text_fields,
        route = CatalogRoute.TextFields.route
    )
)
