package com.instagram.designsystem.core.designsystem.components.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.instagram.designsystem.R

enum class SheetSize {
    Big, Small
}

data class SheetState(
    val id: Int,
    val size: SheetSize,
    val depth: Int = 0,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    size: SheetSize,
    depth: Int,
    onDismiss: () -> Unit,
    onLaunchNext: (SheetSize) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = size == SheetSize.Big
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = if (size == SheetSize.Small) { { /* Default drag handle for small */ } } else null,
        contentWindowInsets = {
            if (size == SheetSize.Big) BottomSheetDefaults.windowInsets else WindowInsets(0, 0, 0, 0)
        },
        modifier = if (size == SheetSize.Big) {
            Modifier
                .fillMaxHeight()
                .padding(top = (depth * 7).dp)
        } else {
            Modifier
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (size == SheetSize.Big) Modifier.fillMaxHeight() else Modifier
                )
                .then(
                    if (size == SheetSize.Small) Modifier.height((400 - (depth * 7)).dp) else Modifier
                )
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (size == SheetSize.Big) "Big Bottom Sheet (Depth: $depth)" else "Small Bottom Sheet (Depth: $depth)",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onDismiss) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = stringResource(R.string.bottom_sheet_text).repeat(50),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onLaunchNext(SheetSize.Big) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Launch Big Sheet")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onLaunchNext(SheetSize.Small) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Launch Small Sheet")
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
