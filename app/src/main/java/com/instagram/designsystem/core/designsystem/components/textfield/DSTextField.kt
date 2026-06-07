package com.instagram.designsystem.core.designsystem.components.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.instagram.designsystem.R

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
 * @param enabled Controls the enabled state of this text field.
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
    enabled: Boolean = true
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

    when (style) {
        TextFieldStyle.Basic -> {
            TextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text(label) },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.padding_small)),
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
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.padding_small)),
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
