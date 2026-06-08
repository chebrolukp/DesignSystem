package com.instagram.designsystem.core.designsystem.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * Combined MultiPreview for common device configurations, themes, and accessibility settings.
 */
@Preview(name = "Light Mode", group = "Themes", showBackground = true)
@Preview(name = "Dark Mode", group = "Themes", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)

@Preview(name = "Small Phone", group = "Devices", device = "spec:width=320dp,height=480dp", showBackground = true)
@Preview(name = "Phone", group = "Devices", device = Devices.PIXEL_4, showBackground = true)
@Preview(name = "Foldable", group = "Devices", device = Devices.FOLDABLE, showBackground = true)
@Preview(name = "Tablet", group = "Devices", device = Devices.TABLET, showBackground = true)

@Preview(name = "Landscape", group = "Orientation", device = "spec:width=915dp,height=412dp,orientation=landscape", showBackground = true)

@Preview(name = "Large Font", group = "Accessibility", fontScale = 1.5f, showBackground = true)
@Preview(name = "Huge Font", group = "Accessibility", fontScale = 2.0f, showBackground = true)

annotation class MultiPreview

/**
 * Separate annotation for just Theme variations (Light/Dark).
 */
@Preview(name = "Light Mode", group = "Themes", showBackground = true)
@Preview(name = "Dark Mode", group = "Themes", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
annotation class ThemePreviews

/**
 * Separate annotation for Device variations.
 */
@Preview(name = "Small Phone", group = "Devices", device = "spec:width=320dp,height=480dp", showBackground = true)
@Preview(name = "Phone", group = "Devices", device = Devices.PIXEL_4, showBackground = true)
@Preview(name = "Foldable", group = "Devices", device = Devices.FOLDABLE, showBackground = true)
@Preview(name = "Tablet", group = "Devices", device = Devices.TABLET, showBackground = true)
annotation class DevicePreviews

/**
 * Separate annotation for Accessibility (Font Scale) variations.
 */
@Preview(name = "Normal Font", group = "Accessibility", fontScale = 1.0f, showBackground = true)
@Preview(name = "Large Font", group = "Accessibility", fontScale = 1.5f, showBackground = true)
@Preview(name = "Huge Font", group = "Accessibility", fontScale = 2.0f, showBackground = true)
annotation class FontPreviews
