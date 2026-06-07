package com.instagram.designsystem.core.designsystem.catalog.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.instagram.designsystem.R
import com.instagram.designsystem.core.designsystem.components.textfield.DSTextField
import com.instagram.designsystem.core.designsystem.components.textfield.TextFieldStyle
import com.instagram.designsystem.core.designsystem.components.textfield.TextFieldVariation

@Composable
fun TextFieldCatalogScreen(modifier: Modifier = Modifier) {
    var basicValue by remember { mutableStateOf("") }
    var outlinedValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }
    var emailValue by remember { mutableStateOf("") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_medium))
    ) {
        item {
            DSTextField(
                value = basicValue,
                onValueChange = { basicValue = it },
                label = stringResource(R.string.label_username),
                style = TextFieldStyle.Basic,
                variation = TextFieldVariation.Default(
                    leadingIcon = Icons.Default.Person,
                    leadingIconContentDescription = "User icon"
                )
            )
        }

        item { Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium))) }

        item {
            DSTextField(
                value = outlinedValue,
                onValueChange = { outlinedValue = it },
                label = stringResource(R.string.label_username),
                style = TextFieldStyle.Outlined,
                placeholder = "john_doe"
            )
        }

        item { Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium))) }

        item {
            DSTextField(
                value = emailValue,
                onValueChange = { emailValue = it },
                label = stringResource(R.string.label_email),
                style = TextFieldStyle.Outlined,
                variation = TextFieldVariation.Default(
                    trailingIcon = Icons.Default.Email,
                    trailingIconContentDescription = "Email icon"
                ),
                placeholder = stringResource(R.string.placeholder_email)
            )
        }

        item { Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium))) }

        item {
            DSTextField(
                value = passwordValue,
                onValueChange = { passwordValue = it },
                label = stringResource(R.string.label_password),
                style = TextFieldStyle.Outlined,
                variation = TextFieldVariation.Password()
            )
        }

        item { Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium))) }

        item {
            DSTextField(
                value = "Invalid input",
                onValueChange = { },
                label = "Error State",
                isError = true,
                enabled = true
            )
        }

        item { Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium))) }

        item {
            DSTextField(
                value = "Disabled",
                onValueChange = { },
                label = "Disabled State",
                enabled = false
            )
        }
    }
}
