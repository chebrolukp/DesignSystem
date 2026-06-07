package com.instagram.designsystem.core.designsystem.catalog.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.instagram.designsystem.R
import com.instagram.designsystem.core.designsystem.components.bottomsheet.DSBottomSheet
import com.instagram.designsystem.core.designsystem.components.bottomsheet.SheetSize
import com.instagram.designsystem.core.designsystem.components.login.DSLogin

@Composable
fun LoginPatternCatalogScreen(
    modifier: Modifier = Modifier,
    onScreenOptionClick: () -> Unit,
) {
    var showModal by remember { mutableStateOf(value = false) }
    var serverError by remember { mutableStateOf<String?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                ListItem(
                    headlineContent = { Text(stringResource(R.string.login_option_screen)) },
                    modifier = Modifier.clickable { onScreenOptionClick() }
                )
            }
            item {
                ListItem(
                    headlineContent = { Text(stringResource(R.string.login_option_modal)) },
                    modifier = Modifier.clickable { showModal = true }
                )
            }
        }

        if (showModal) {
            DSBottomSheet(
                size = SheetSize.Big,
                depth = 0,
                title = "", // Hide the default title for the login modal
                onDismiss = { 
                    showModal = false
                    serverError = null
                }
            ) {
                DSLogin(
                    onLoginClick = { email, _ ->
                        if (email == "error@test.com") {
                            serverError = "Server Error: Access Denied"
                        } else {
                            showModal = false
                            serverError = null
                        }
                    },
                    serverError = serverError,
                    onFieldChange = { serverError = null }
                )
            }
        }
    }
}
