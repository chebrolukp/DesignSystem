package com.instagram.designsystem.core.designsystem.components

import com.instagram.designsystem.core.designsystem.navigation.CatalogRoute

data class ComponentCategory(
    val title: String,
    val route: String
)

val categories = listOf(
    ComponentCategory(
        title = "Buttons",
        route = CatalogRoute.Buttons.route
    ),
    ComponentCategory(
        title = "Top Bars",
        route = CatalogRoute.TopBars.route
    ),
    ComponentCategory(
        title = "Bottom Sheets",
        route = CatalogRoute.BottomSheets.route
    )
)