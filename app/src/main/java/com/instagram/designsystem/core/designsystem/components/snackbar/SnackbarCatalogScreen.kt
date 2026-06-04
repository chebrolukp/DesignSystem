package com.instagram.designsystem.core.designsystem.components.snackbar

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.instagram.designsystem.R
import com.instagram.designsystem.core.designsystem.components.animation.dsEnterFromRight
import com.instagram.designsystem.core.designsystem.components.animation.dsExitToRight
import kotlinx.coroutines.delay

@Composable
fun SnackbarCatalogScreen(modifier: Modifier = Modifier) {
    val snackbarQueue = remember { mutableStateListOf<SnackbarData>() }
    var currentSnackbar by remember { mutableStateOf<SnackbarData?>(null) }
    var nextId by remember { mutableLongStateOf(0L) }

    val basicText = stringResource(R.string.basic_snackbar_text)
    val iconText = stringResource(R.string.icon_snackbar_text)
    val complexText = stringResource(R.string.complex_snackbar_text)
    val option1Text = stringResource(R.string.option_1)
    val option2Text = stringResource(R.string.option_2)

    // Logic to show snackbars one by one
    LaunchedEffect(currentSnackbar) {
        if (currentSnackbar == null) {
            if (snackbarQueue.isNotEmpty()) {
                currentSnackbar = snackbarQueue.removeAt(0)
            }
        } else {
            delay(currentSnackbar?.durationMillis ?: 4000L)
            currentSnackbar = if (snackbarQueue.isNotEmpty()) {
                snackbarQueue.removeAt(0)
            } else {
                null
            }
        }
    }

    // Trigger when items are added to an empty queue
    LaunchedEffect(snackbarQueue.size) {
        if (currentSnackbar == null && snackbarQueue.isNotEmpty()) {
            currentSnackbar = snackbarQueue.removeAt(0)
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                snackbarQueue.add(
                    SnackbarData(
                        id = nextId++,
                        variation = SnackbarVariation.Basic(basicText)
                    )
                )
            }) {
                Text(stringResource(R.string.launch_basic_snackbar))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                snackbarQueue.add(
                    SnackbarData(
                        id = nextId++,
                        variation = SnackbarVariation.WithIcon(
                            text = iconText,
                            icon = Icons.Default.Notifications,
                            iconContentDescription = "Notifications"
                        )
                    )
                )
            }) {
                Text(stringResource(R.string.launch_icon_snackbar))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                snackbarQueue.add(
                    SnackbarData(
                        id = nextId++,
                        variation = SnackbarVariation.Complex(
                            icon = Icons.Default.Star,
                            iconContentDescription = "Star",
                            text = complexText,
                            topActionText = option1Text,
                            bottomActionText = option2Text,
                            onTopActionClick = { println("Top Clicked") },
                            onBottomActionClick = { println("Bottom Clicked") }
                        )
                    )
                )
            }) {
                Text(stringResource(R.string.launch_complex_snackbar))
            }
        }

        // Display current snackbar at the bottom with slide-in from right
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = dimensionResource(R.dimen.padding_medium)),
            contentAlignment = Alignment.BottomCenter
        ) {
            AnimatedContent(
                targetState = currentSnackbar,
                modifier = Modifier.fillMaxWidth(),
                transitionSpec = {
                    if (targetState != null) {
                        // New snackbar entering: Slide in from right
                        dsEnterFromRight() togetherWith dsExitToRight()
                    } else {
                        // Snackbar being removed (timer or swipe): 
                        // If it was a swipe, DSSnackbar already moved it. 
                        // Fade out looks good for both cases.
                        dsEnterFromRight() togetherWith fadeOut()
                    }
                },
                label = "snackbarAnimation"
            ) { snackbarData ->
                if (snackbarData != null) {
                    DSSnackbar(
                        data = snackbarData,
                        onDismiss = {
                            if (currentSnackbar?.id == snackbarData.id) {
                                currentSnackbar = null
                            }
                        }
                    )
                } else {
                    // Placeholder to keep the width stable during transitions
                    Spacer(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}
