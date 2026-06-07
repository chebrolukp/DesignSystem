package com.instagram.designsystem.core.designsystem.components.textfield

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Defines the different visual styles for the [DSTextField] component.
 */
sealed class TextFieldStyle {
    /**
     * A basic text field with a filled background.
     */
    data object Basic : TextFieldStyle()

    /**
     * An outlined text field with a border.
     */
    data object Outlined : TextFieldStyle()
}

/**
 * Defines the functional variations for the [DSTextField] component.
 *
 * @property leadingIcon Optional icon to be displayed at the start of the text field.
 * @property leadingIconContentDescription Mandatory accessibility description if a leading icon is provided.
 * @property trailingIcon Optional icon to be displayed at the end of the text field.
 * @property trailingIconContentDescription Mandatory accessibility description if a trailing icon is provided.
 */
sealed class TextFieldVariation(
    val leadingIcon: ImageVector? = null,
    val leadingIconContentDescription: String? = null,
    val trailingIcon: ImageVector? = null,
    val trailingIconContentDescription: String? = null
) {
    /**
     * A standard text input field.
     */
    class Default(
        leadingIcon: ImageVector? = null,
        leadingIconContentDescription: String? = null,
        trailingIcon: ImageVector? = null,
        trailingIconContentDescription: String? = null
    ) : TextFieldVariation(leadingIcon, leadingIconContentDescription, trailingIcon, trailingIconContentDescription)

    /**
     * A text field optimized for sensitive information like passwords.
     */
    class Password(
        leadingIcon: ImageVector? = null,
        leadingIconContentDescription: String? = null
    ) : TextFieldVariation(leadingIcon, leadingIconContentDescription)
}
