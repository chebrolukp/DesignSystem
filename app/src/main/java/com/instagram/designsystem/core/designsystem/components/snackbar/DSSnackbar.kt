package com.instagram.designsystem.core.designsystem.components.snackbar

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import com.instagram.designsystem.R
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

sealed class SnackbarVariation {
    data class Basic(val text: String) : SnackbarVariation()
    data class WithIcon(
        val text: String,
        val icon: ImageVector,
        val iconContentDescription: String
    ) : SnackbarVariation()
    data class Complex(
        val icon: ImageVector,
        val iconContentDescription: String,
        val text: String,
        val topActionText: String,
        val bottomActionText: String,
        val onTopActionClick: () -> Unit,
        val onBottomActionClick: () -> Unit,
    ) : SnackbarVariation()
}

data class SnackbarData(
    val id: Long,
    val variation: SnackbarVariation,
    val durationMillis: Long = 4000 // Standard "Short" duration
)

@Composable
fun DSSnackbar(
    data: SnackbarData,
    onDismiss: () -> Unit
) {
    val offsetX = remember { Animatable(initialValue = 0f) }
    val offsetY = remember { Animatable(initialValue = 0f) }
    val scope = rememberCoroutineScope()
    val isDismissing = remember { mutableStateOf(false) }

    if (isDismissing.value) return

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_medium))
            .semantics {
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = "Dismiss snackbar",
                        action = { onDismiss(); true }
                    )
                )
            }
            .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        scope.launch {
                            if (offsetX.value > 300) {
                                // Swipe Right
                                offsetX.animateTo(1000f)
                                onDismiss()
                            } else if (offsetX.value < -300) {
                                // Swipe Left
                                offsetX.animateTo(-1000f)
                                onDismiss()
                            } else if (offsetY.value > 200) {
                                // Swipe Down
                                offsetY.animateTo(1000f)
                                onDismiss()
                            } else {
                                // Reset
                                launch { offsetX.animateTo(0f) }
                                launch { offsetY.animateTo(0f) }
                            }
                        }
                    }
                ) { change, dragAmount ->
                    change.consume()
                    scope.launch {
                        offsetX.snapTo(offsetX.value + dragAmount.x)
                        // Allow dragging down (positive y)
                        if (offsetY.value + dragAmount.y >= 0) {
                            offsetY.snapTo(offsetY.value + dragAmount.y)
                        }
                    }
                }
            }
            .background(Color.Black, RoundedCornerShape(dimensionResource(R.dimen.snackbar_corner_radius)))
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        when (val variation = data.variation) {
            is SnackbarVariation.Basic -> {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(
                        text = variation.text,
                        color = Color(0xFF00BFFF),
                        fontSize = 16.sp // Sp cannot easily be dimenResource for text size without complexity
                    )
                }
            }
            is SnackbarVariation.WithIcon -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = variation.text, color = Color.White, modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = variation.icon,
                        contentDescription = variation.iconContentDescription,
                        tint = Color.White
                    )
                }
            }
            is SnackbarVariation.Complex -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = variation.icon,
                        contentDescription = variation.iconContentDescription,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(text = variation.text, color = Color.White, modifier = Modifier.weight(1f))
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(horizontalAlignment = Alignment.End) {
                        ActionTextBox(text = variation.topActionText, onClick = variation.onTopActionClick)
                        Spacer(modifier = Modifier.height(8.dp))
                        ActionTextBox(text = variation.bottomActionText, onClick = variation.onBottomActionClick)
                    }
                }
            }
        }
    }
}

@Composable
fun ActionTextBox(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .border(1.dp, Color(0xFF00BFFF), RoundedCornerShape(4.dp))
            .clickable { onClick() }
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(text = text, color = Color(0xFF00BFFF), fontSize = 12.sp)
    }
}
