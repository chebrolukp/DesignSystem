package com.instagram.designsystem.core.designsystem.components.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Animation spec for expanding components (e.g., top bars, menus).
 */
fun <T> dsExpand() = dsSpringSpec<T>()

/**
 * Animation spec for collapsing components.
 */
fun <T> dsCollapse() = dsSpringSpec<T>()

/**
 * Standard spring animation spec used across the design system.
 */
private fun <T> dsSpringSpec() = spring<T>(
    stiffness = Spring.StiffnessLow,
    dampingRatio = Spring.DampingRatioNoBouncy
)

/**
 * Standard Enter transition from the right of the screen.
 */
fun dsEnterFromRight(): EnterTransition {
    return slideInHorizontally { width -> width } + fadeIn()
}

/**
 * Standard Exit transition to the right of the screen.
 */
fun dsExitToRight(): ExitTransition {
    return slideOutHorizontally { width -> width } + fadeOut()
}

/**
 * A reusable wrapper for components that should animate in from the right.
 */
@Composable
fun SlideInRightAnimation(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = dsEnterFromRight(),
        exit = dsExitToRight(),
        modifier = modifier
    ) {
        content()
    }
}
