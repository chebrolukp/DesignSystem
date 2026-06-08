package com.instagram.designsystem.core.designsystem.components.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.instagram.designsystem.R
import com.instagram.designsystem.core.designsystem.components.banner.DSBanner
import com.instagram.designsystem.core.designsystem.components.button.DSButton
import com.instagram.designsystem.core.designsystem.components.text.DSText
import com.instagram.designsystem.core.designsystem.components.text.TextVariation
import com.instagram.designsystem.core.designsystem.components.textfield.DSTextField
import com.instagram.designsystem.core.designsystem.components.textfield.TextFieldVariation

/**
 * A reusable login pattern component that can be used as a standalone screen or within a modal.
 *
 * @param onLoginClick Called when the login button is clicked and local validation passes.
 * Passes the entered email and password.
 * @param modifier The [Modifier] to be applied to the root container.
 * @param serverError Optional error message to be displayed in a global banner (e.g., after an API failure).
 * @param onFieldChange Called when any field value changes (useful for clearing server errors).
 */
@Composable
fun DSLogin(
    onLoginClick: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    serverError: String? = null,
    onFieldChange: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    
    var emailHasLostFocus by remember { mutableStateOf(false) }
    var passwordHasLostFocus by remember { mutableStateOf(false) }

    // Email validation
    val errorEmailMissingAt = stringResource(R.string.error_email_missing_at)
    val errorEmailInvalidChars = stringResource(R.string.error_email_invalid_chars)
    
    val emailError = remember(email, emailHasLostFocus) {
        val hasInvalidChars = email.any { !it.isLetterOrDigit() && it != '@' && it != '.' } || email.contains("&#")
        when {
            email.isEmpty() -> null
            hasInvalidChars -> errorEmailInvalidChars
            !email.contains("@") && emailHasLostFocus -> errorEmailMissingAt
            else -> null
        }
    }

    // Password validation
    val errorPasswordLength = stringResource(R.string.error_password_length)
    val errorPasswordUppercase = stringResource(R.string.error_password_uppercase)
    val errorPasswordLowercase = stringResource(R.string.error_password_lowercase)
    val errorPasswordNumber = stringResource(R.string.error_password_number)
    val errorPasswordSpecial = stringResource(R.string.error_password_special)

    val passwordError = remember(password, passwordHasLostFocus) {
        val hasUpper = password.any { it.isUpperCase() }
        val hasLower = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecial = password.any { !it.isLetterOrDigit() }
        val hasImmediateError = password.contains("&#")
        
        when {
            password.isEmpty() -> null
            hasImmediateError -> "Invalid string &#"
            passwordHasLostFocus -> {
                when {
                    password.length < 8 -> errorPasswordLength
                    !hasUpper -> errorPasswordUppercase
                    !hasLower -> errorPasswordLowercase
                    !hasDigit -> errorPasswordNumber
                    !hasSpecial -> errorPasswordSpecial
                    else -> null
                }
            }
            else -> null
        }
    }

    Column(
        modifier = modifier
            .widthIn(max = 480.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Global Server Error at the top
        if (serverError != null) {
            DSBanner(
                message = serverError,
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
        }

        DSText(
            text = stringResource(R.string.login_screen_title),
            variation = TextVariation.Headline,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_large))
        )

        DSTextField(
            value = email,
            onValueChange = { 
                email = it
                onFieldChange()
            },
            onFocusChanged = { isFocused ->
                if (!isFocused && email.isNotEmpty()) {
                    emailHasLostFocus = true
                }
            },
            label = stringResource(R.string.label_email),
            isError = emailError != null,
            errorMessage = emailError
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

        DSTextField(
            value = password,
            onValueChange = { 
                password = it
                onFieldChange()
            },
            onFocusChanged = { isFocused ->
                if (!isFocused && password.isNotEmpty()) {
                    passwordHasLostFocus = true
                }
            },
            label = stringResource(R.string.label_password),
            variation = TextFieldVariation.Password(),
            isError = passwordError != null,
            errorMessage = passwordError
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

        DSButton(
            text = stringResource(R.string.login),
            onClick = {
                // Ensure all validation is shown on click
                emailHasLostFocus = true
                passwordHasLostFocus = true

                if (emailError == null && passwordError == null && email.isNotEmpty() && password.isNotEmpty()) {
                    onLoginClick(email, password)
                }
            }
        )
    }
}
