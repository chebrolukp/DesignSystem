package com.instagram.designsystem.core.designsystem.components.topBar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.instagram.designsystem.R
import com.instagram.designsystem.core.designsystem.components.button.ButtonStyle
import com.instagram.designsystem.core.designsystem.components.button.ButtonVariation
import com.instagram.designsystem.core.designsystem.components.button.DSButton
import com.instagram.designsystem.core.designsystem.components.text.DSText
import com.instagram.designsystem.core.designsystem.components.text.TextVariation

/**
 * A reusable TopAppBar component that supports different variations like Simple, Back, and Action.
 *
 * @param variant The [TopBarVariant] to be displayed.
 * @param modifier The modifier to be applied to the top app bar.
 * @param windowInsets The window insets to be applied to the top app bar.
 */
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
                    DSText(variant.title, TextVariation.Title)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            )
        }

        is TopBarVariant.Expanded -> {
            TopAppBar(
                modifier = modifier,
                windowInsets = windowInsets,
                title = {
                    DSText(variant.title, TextVariation.Title)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            )
        }

        is TopBarVariant.Back -> {
            TopAppBar(
                modifier = modifier,
                windowInsets = windowInsets,
                title = {
                    DSText(variant.title, TextVariation.Title)
                },
                navigationIcon = {
                    DSButton(
                        text = stringResource(R.string.back),
                        onClick = variant.onBackClick,
                        style = ButtonStyle.IconOnly(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        ),
                        variant = ButtonVariation.Ghost
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            )
        }

        is TopBarVariant.Action -> {
            TopAppBar(
                modifier = modifier,
                windowInsets = windowInsets,
                title = {
                    DSText(variant.title, TextVariation.Title)
                },
                actions = {
                    DSButton(
                        text = stringResource(R.string.add),
                        onClick = variant.onActionClick,
                        style = ButtonStyle.IconOnly(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(R.string.add)
                        ),
                        variant = ButtonVariation.Ghost
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                )
            )
        }

        is TopBarVariant.NavigationAction -> {
            TopAppBar(
                modifier = modifier,
                windowInsets = windowInsets,
                title = {
                    DSText(
                        text = variant.title,
                        variation = TextVariation.Title,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                navigationIcon = {
                    DSButton(
                        text = stringResource(R.string.back),
                        onClick = variant.onBackClick,
                        style = ButtonStyle.IconOnly(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        ),
                        variant = ButtonVariation.Ghost,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                actions = {
                    DSButton(
                        text = stringResource(R.string.add),
                        onClick = variant.onActionClick,
                        style = ButtonStyle.IconOnly(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(R.string.add)
                        ),
                        variant = ButtonVariation.Ghost,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    }
}
