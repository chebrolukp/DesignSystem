package com.instagram.designsystem.core.designsystem.foundation.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Standard spring animation spec used across the design system for expansion transitions.
 */
fun <T> dsExpand() = dsSpringSpec<T>()

/**
 * Standard spring animation spec used across the design system for collapse transitions.
 */
fun <T> dsCollapse() = dsSpringSpec<T>()

/**
 * Low-level spring configuration shared by semantic animation specs.
 */
private fun <T> dsSpringSpec() = spring<T>(
    stiffness = Spring.StiffnessLow,
    dampingRatio = Spring.DampingRatioNoBouncy
)

/**
 * Standard Enter transition that slides a component in from the right edge
 * while simultaneously fading it in.
 */
fun dsEnterFromRight(): EnterTransition {
    return slideInHorizontally { width -> width } + fadeIn()
}

/**
 * Standard Exit transition that slides a component out to the right edge
 * while simultaneously fading it out.
 */
fun dsExitToRight(): ExitTransition {
    return slideOutHorizontally { width -> width } + fadeOut()
}

/**
 * A reusable container that applies the standard [dsEnterFromRight] and [dsExitToRight]
 * transitions to its content.
 *
 * @param visible Whether the content should be visible.
 * @param modifier The modifier to be applied to the animation container.
 * @param content The composable content to be animated.
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
