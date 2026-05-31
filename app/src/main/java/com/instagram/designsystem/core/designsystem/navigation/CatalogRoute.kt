package com.instagram.designsystem.core.designsystem.navigation

sealed class CatalogRoute(val route: String) {
    data object Home : CatalogRoute("home")
    data object Buttons : CatalogRoute("buttons")
    data object TopBars : CatalogRoute("topbars")
    data object BottomSheets : CatalogRoute("bottomsheets")
}