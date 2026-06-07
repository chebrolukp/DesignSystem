package com.instagram.designsystem.core.designsystem.catalog.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.instagram.designsystem.core.designsystem.catalog.navigation.CatalogRoute
import com.instagram.designsystem.core.designsystem.catalog.screens.BottomSheetCatalogScreen
import com.instagram.designsystem.core.designsystem.catalog.screens.ButtonCatalogScreen
import com.instagram.designsystem.core.designsystem.catalog.screens.CatalogHomeScreen
import com.instagram.designsystem.core.designsystem.catalog.screens.ExpandableTopBarCatalogScreen
import com.instagram.designsystem.core.designsystem.catalog.screens.LoginPatternCatalogScreen
import com.instagram.designsystem.core.designsystem.catalog.screens.SnackbarCatalogScreen
import com.instagram.designsystem.core.designsystem.catalog.screens.TextFieldCatalogScreen
import com.instagram.designsystem.core.designsystem.catalog.screens.TopBarCatalogScreen
import com.instagram.designsystem.core.designsystem.components.login.DSLogin

@Composable
fun CatalogNavHost(padding: PaddingValues) {
    val navController = rememberNavController()
    val modifier = Modifier.padding(padding).padding(horizontal = 15.dp, vertical = 15.dp)
    NavHost(
        navController = navController,
        startDestination = CatalogRoute.Home.route
    ) {
        composable(CatalogRoute.Home.route) {
            CatalogHomeScreen(modifier,
                onItemClick = {
                    navController.navigate(it)
                }
            )
        }

        composable(CatalogRoute.Buttons.route) {
            ButtonCatalogScreen(modifier)
        }

        composable(CatalogRoute.TopBars.route) {
            TopBarCatalogScreen(modifier)
        }

        composable(CatalogRoute.ExpandableTopBar.route) {
            ExpandableTopBarCatalogScreen(modifier)
        }

        composable(CatalogRoute.BottomSheets.route) {
            BottomSheetCatalogScreen(modifier)
        }

        composable(CatalogRoute.Snackbars.route) {
            SnackbarCatalogScreen(modifier)
        }

        composable(CatalogRoute.TextFields.route) {
            TextFieldCatalogScreen(modifier)
        }

        composable(CatalogRoute.LoginPattern.route) {
            LoginPatternCatalogScreen(
                modifier = modifier,
                onScreenOptionClick = { navController.navigate(CatalogRoute.LoginScreen.route) }
            )
        }

        composable(CatalogRoute.LoginScreen.route) {
            var serverError by remember { mutableStateOf<String?>(null) }
            DSLogin(
                modifier = modifier.fillMaxSize(),
                serverError = serverError,
                onFieldChange = { serverError = null },
                onLoginClick = { email, _ ->
                    if (email == "error@test.com") {
                        serverError = "Server Error: Access Denied"
                    } else {
                        navController.popBackStack()
                    }
                }
            )
        }
    }
}
