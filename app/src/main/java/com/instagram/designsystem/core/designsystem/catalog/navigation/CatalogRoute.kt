package com.instagram.designsystem.core.designsystem.catalog.navigation

sealed class CatalogRoute(val route: String) {
    data object Home : CatalogRoute("home")
    data object Buttons : CatalogRoute("buttons")
    data object TopBars : CatalogRoute("topbars")
    data object ExpandableTopBar : CatalogRoute("expandable_topbar")
    data object BottomSheets : CatalogRoute("bottomsheets")
    data object Snackbars : CatalogRoute("snackbars")
    data object TextFields : CatalogRoute("textfields")
    data object LoginPattern : CatalogRoute("login_pattern")
    data object LoginScreen : CatalogRoute("login_screen")
}
