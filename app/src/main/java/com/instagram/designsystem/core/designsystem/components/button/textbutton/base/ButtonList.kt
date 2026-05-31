package com.instagram.designsystem.core.designsystem.components.button.textbutton.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.instagram.designsystem.core.designsystem.components.button.textbutton.PrimaryAppButton
import com.instagram.designsystem.core.designsystem.components.button.textbutton.SecondaryAppButton

@Composable
fun ButtonCatalogScreen(modifier: Modifier) {
    LazyColumn(
        modifier = modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Primary")
                PrimaryAppButton(
                    text = "Enabled"
                )
                PrimaryAppButton(
                    text = "Disabled",
                    enabled = false
                )
            }
        }
        item {
            HorizontalDivider()
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Secondary")
                SecondaryAppButton(
                    text = "Enabled"
                )
                SecondaryAppButton(
                    text = "Disabled",
                    enabled = false
                )
            }
        }
    }
}