package com.instagram.designsystem.core.designsystem.components.button.textbutton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.instagram.designsystem.core.designsystem.components.button.textbutton.base.AppButton
import com.instagram.designsystem.core.designsystem.preview.MultiPreview

@Composable
fun SecondaryAppButton(text: String, enabled: Boolean = true, onClick: () -> Unit = {}) {
    AppButton(
        text = text, backgroundColor = MaterialTheme.colorScheme.secondary,
        textColor = MaterialTheme.colorScheme.onSecondary, enabled = enabled, onClick = onClick
    )
}

@Composable
@MultiPreview
fun SecondaryAppButtonPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        SecondaryAppButton("primary enabled")
        SecondaryAppButton("primary disabled", false)
    }
}
