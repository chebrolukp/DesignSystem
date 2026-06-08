package com.instagram.designsystem.core.designsystem.components.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.instagram.designsystem.core.designsystem.components.topBar.DSTopBar
import com.instagram.designsystem.core.designsystem.components.topBar.TopBarVariant
import com.instagram.designsystem.core.designsystem.foundation.animation.SlideInRightAnimation

/**
 * A standard screen layout component that provides a consistent structure with a top bar
 * and entry animations.
 *
 * @param topBarVariant The [TopBarVariant] configuration for the screen's header.
 * @param modifier The [Modifier] to be applied to the root container.
 * @param content The composable content to be displayed in the screen body.
 */
@Composable
fun DSScreen(
    topBarVariant: TopBarVariant? = null,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    var isVisible by remember { mutableStateOf(false) }

    // Trigger entry animation on mount
    LaunchedEffect(Unit) {
        isVisible = true
    }

    Scaffold(
        topBar = {
            topBarVariant?.let {
                DSTopBar(variant = it)
            }
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        SlideInRightAnimation(visible = isVisible) {
            Box(modifier = Modifier.fillMaxSize()) {
                content(innerPadding)
            }
        }
    }
}
