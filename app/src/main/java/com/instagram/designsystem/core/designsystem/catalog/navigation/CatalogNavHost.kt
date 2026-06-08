package com.instagram.designsystem.core.designsystem.catalog.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import com.instagram.designsystem.core.designsystem.components.screen.DSScreen
import com.instagram.designsystem.core.designsystem.components.topBar.TopBarVariant
import com.instagram.designsystem.core.designsystem.foundation.theme.DesignSystemSpacing

@Composable
fun CatalogNavHost(windowSizeClass: WindowSizeClass) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = CatalogRoute.Home.route
    ) {
        composable(CatalogRoute.Home.route) {
            DSScreen(
                topBarVariant = TopBarVariant.Simple(title = stringResource(com.instagram.designsystem.R.string.app_name))
            ) { padding ->
                CatalogHomeScreen(
                    windowSizeClass = windowSizeClass,
                    modifier = Modifier.padding(padding).padding(DesignSystemSpacing.Medium),
                    onItemClick = {
                        navController.navigate(it)
                    }
                )
            }
        }

        composable(CatalogRoute.Buttons.route) {
            DSScreen(
                topBarVariant = TopBarVariant.Back(
                    title = stringResource(com.instagram.designsystem.R.string.category_buttons),
                    onBackClick = { navController.popBackStack() }
                )
            ) { padding ->
                ButtonCatalogScreen(Modifier.padding(padding).padding(DesignSystemSpacing.Medium))
            }
        }

        composable(CatalogRoute.TopBars.route) {
            DSScreen(
                topBarVariant = TopBarVariant.Back(
                    title = stringResource(com.instagram.designsystem.R.string.category_top_bars),
                    onBackClick = { navController.popBackStack() }
                )
            ) { padding ->
                TopBarCatalogScreen(Modifier.padding(padding).padding(DesignSystemSpacing.Medium))
            }
        }

        composable(CatalogRoute.ExpandableTopBar.route) {
            // Expandable top bar manages its own scaffold-like behavior or we can wrap it
            ExpandableTopBarCatalogScreen(Modifier.fillMaxSize())
        }

        composable(CatalogRoute.BottomSheets.route) {
            DSScreen(
                topBarVariant = TopBarVariant.Back(
                    title = stringResource(com.instagram.designsystem.R.string.category_bottom_sheets),
                    onBackClick = { navController.popBackStack() }
                )
            ) { padding ->
                BottomSheetCatalogScreen(Modifier.padding(padding).padding(DesignSystemSpacing.Medium))
            }
        }

        composable(CatalogRoute.Snackbars.route) {
            DSScreen(
                topBarVariant = TopBarVariant.Back(
                    title = stringResource(com.instagram.designsystem.R.string.category_snackbars),
                    onBackClick = { navController.popBackStack() }
                )
            ) { padding ->
                SnackbarCatalogScreen(Modifier.padding(padding).padding(DesignSystemSpacing.Medium))
            }
        }

        composable(CatalogRoute.TextFields.route) {
            DSScreen(
                topBarVariant = TopBarVariant.Back(
                    title = stringResource(com.instagram.designsystem.R.string.category_text_fields),
                    onBackClick = { navController.popBackStack() }
                )
            ) { padding ->
                TextFieldCatalogScreen(Modifier.padding(padding).padding(DesignSystemSpacing.Medium))
            }
        }

        composable(CatalogRoute.LoginPattern.route) {
            DSScreen(
                topBarVariant = TopBarVariant.Back(
                    title = stringResource(com.instagram.designsystem.R.string.category_login_screen),
                    onBackClick = { navController.popBackStack() }
                )
            ) { padding ->
                LoginPatternCatalogScreen(
                    modifier = Modifier.padding(padding).padding(DesignSystemSpacing.Medium),
                    onScreenOptionClick = { navController.navigate(CatalogRoute.LoginScreen.route) }
                )
            }
        }

        composable(CatalogRoute.LoginScreen.route) {
            var serverError by remember { mutableStateOf<String?>(null) }
            DSScreen(
                topBarVariant = TopBarVariant.Back(
                    title = stringResource(com.instagram.designsystem.R.string.login),
                    onBackClick = { navController.popBackStack() }
                )
            ) { padding ->
                DSLogin(
                    modifier = Modifier.padding(padding).padding(DesignSystemSpacing.Medium).fillMaxSize(),
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
}
