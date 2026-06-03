package com.instagram.designsystem.core.designsystem.components.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomSheetCatalogScreen(modifier: Modifier = Modifier) {
    val sheets = remember { mutableStateListOf<SheetState>() }
    var nextId by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                sheets.add(SheetState(id = nextId++, size = SheetSize.Big, depth = 0))
            }
        ) {
            Text("Launch Big Dialog")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                sheets.add(SheetState(id = nextId++, size = SheetSize.Small, depth = 0))
            }
        ) {
            Text("Launch Small Dialog")
        }
    }

    // Render active sheets
    sheets.forEachIndexed { index, sheet ->
        AppBottomSheet(
            size = sheet.size,
            depth = sheet.depth,
            onDismiss = {
                sheets.removeAt(index)
            }
        ) { size ->
            sheets.add(
                SheetState(
                    id = nextId++,
                    size = size,
                    depth = if (size == sheet.size) sheet.depth + 1 else 0
                )
            )
        }
    }
}
