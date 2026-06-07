package com.instagram.designsystem.core.designsystem.components.textfield

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.instagram.designsystem.R
import com.instagram.designsystem.core.designsystem.components.text.DSText
import com.instagram.designsystem.core.designsystem.components.text.TextVariation

/**
 * A reusable text field component with customizable styles and variations.
 *
 * @param value The text to be shown in the text field.
 * @param onValueChange Called when the text in the field is changed.
 * @param label The label to be displayed inside the text field container.
 * @param modifier The [Modifier] to be applied to this text field.
 * @param style The visual style of the field, either [TextFieldStyle.Basic] or [TextFieldStyle.Outlined].
 * @param variation The functional variation, either [TextFieldVariation.Default] or [TextFieldVariation.Password].
 * @param placeholder The text to be displayed when the field is empty.
 * @param isError Indicates if the text field's current value is in error.
 * @param errorMessage Optional error message to be displayed below the field when [isError] is true.
 * @param enabled Controls the enabled state of this text field.
 * @param onFocusChanged Called when the focus state of the text field changes.
 */
@Composable
fun DSTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    style: TextFieldStyle = TextFieldStyle.Outlined,
    variation: TextFieldVariation = TextFieldVariation.Default(),
    placeholder: String? = null,
    isError: Boolean = false,
    errorMessage: String? = null,
    enabled: Boolean = true,
    onFocusChanged: (Boolean) -> Unit = {}
) {
    var passwordVisible by remember { mutableStateOf(false) }

    val leadingIcon: @Composable (() -> Unit)? = variation.leadingIcon?.let { icon ->
        {
            Icon(
                imageVector = icon,
                contentDescription = variation.leadingIconContentDescription
            )
        }
    }

    val trailingIcon: @Composable (() -> Unit)? = when (variation) {
        is TextFieldVariation.Password -> {
            {
                val icon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) {
                    stringResource(R.string.hide_password)
                } else {
                    stringResource(R.string.show_password)
                }
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = description)
                }
            }
        }
        else -> variation.trailingIcon?.let { icon ->
            {
                Icon(
                    imageVector = icon,
                    contentDescription = variation.trailingIconContentDescription
                )
            }
        }
    }

    val visualTransformation = if (variation is TextFieldVariation.Password && !passwordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                when (style) {
                    TextFieldStyle.Basic -> {
                        TextField(
                            value = value,
                            onValueChange = onValueChange,
                            label = { Text(label) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onFocusChanged { onFocusChanged(it.isFocused) },
                            placeholder = placeholder?.let { { Text(it) } },
                            leadingIcon = leadingIcon,
                            trailingIcon = trailingIcon,
                            isError = isError,
                            enabled = enabled,
                            visualTransformation = visualTransformation,
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline
                            )
                        )
                    }
                    TextFieldStyle.Outlined -> {
                        OutlinedTextField(
                            value = value,
                            onValueChange = onValueChange,
                            label = { Text(label) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .onFocusChanged { onFocusChanged(it.isFocused) },
                            placeholder = placeholder?.let { { Text(it) } },
                            leadingIcon = leadingIcon,
                            trailingIcon = trailingIcon,
                            isError = isError,
                            enabled = enabled,
                            visualTransformation = visualTransformation,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            )
                        )
                    }
                }
            }

            if (isError) {
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }

        // Error message area with reserved height to avoid UI jump
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 20.dp) // Reserves space even when empty
        ) {
            if (isError && !errorMessage.isNullOrEmpty()) {
                DSText(
                    text = errorMessage,
                    variation = TextVariation.Error,
                    modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium))
                )
            }
        }
    }
}
