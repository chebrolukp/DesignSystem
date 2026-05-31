package com.instagram.designsystem.core.designsystem.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

//Devices
@Preview(
    name = "Phone Light",
    widthDp = 412,
    heightDp = 915,
    showBackground = true
)
@Preview(
    name = "Phone Dark",
    widthDp = 412,
    heightDp = 915,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Preview(
    name = "Tablet Light",
    widthDp = 840,
    heightDp = 1280,
    showBackground = true
)
@Preview(
    name = "Tablet Dark",
    widthDp = 840,
    heightDp = 1280,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)

//Fonts
@Preview(
    name = "Large Font",
    fontScale = 1.5f,
    showBackground = true
)

@Preview(
    name = "Huge Font",
    fontScale = 2f,
    showBackground = true
)

annotation class MultiPreview