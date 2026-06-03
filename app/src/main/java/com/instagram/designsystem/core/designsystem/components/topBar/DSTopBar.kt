package com.instagram.designsystem.core.designsystem.components.topBar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DSTopBar(
    variant: TopBarVariant,
    modifier: Modifier = Modifier,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets
) {
    when (variant) {

        is TopBarVariant.Simple -> {
            CenterAlignedTopAppBar(
                modifier = modifier,
                windowInsets = windowInsets,
                title = {
                    Text(variant.title)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray
                )
            )
        }

        is TopBarVariant.Expanded -> {
            TopAppBar(
                modifier = modifier,
                windowInsets = windowInsets,
                title = {
                    Text(variant.title)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray
                )
            )
        }

        is TopBarVariant.Back -> {
            TopAppBar(
                modifier = modifier,
                windowInsets = windowInsets,
                title = {
                    Text(variant.title)
                },
                navigationIcon = {
                    IconButton(
                        onClick = variant.onBackClick
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray
                )
            )
        }

        is TopBarVariant.Action -> {
            TopAppBar(
                modifier = modifier,
                windowInsets = windowInsets,
                title = {
                    Text(variant.title)
                },
                actions = {
                    IconButton(
                        onClick = variant.onActionClick
                    ) {
                        Icon(Icons.Default.Add, null)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray
                )
            )
        }
    }
}
