package com.instagram.designsystem.core.designsystem.components.button.textbutton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.instagram.designsystem.core.designsystem.components.button.textbutton.base.AppButton
import com.instagram.designsystem.core.designsystem.preview.MultiPreview

@Composable
fun PrimaryAppButton(text: String, enabled: Boolean = true, onClick: () -> Unit = {}) {
    AppButton(text = text, enabled = enabled, onClick = onClick)
}

@Composable
@MultiPreview
fun PrimaryAppButtonPreview(){
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        PrimaryAppButton("primary enabled")
        PrimaryAppButton("primary disabled", false)
    }
}
