package com.instagram.designsystem.core.designsystem.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.instagram.designsystem.core.designsystem.components.button.textbutton.base.ButtonCatalogScreen
import com.instagram.designsystem.feature.presentation.screen.CatalogHomeScreen

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

//        composable(CatalogRoute.TopBars.route) {
//            TopBarCatalogScreen()
//        }
//
//        composable(CatalogRoute.BottomSheets.route) {
//            BottomSheetCatalogScreen()
//        }
    }
}