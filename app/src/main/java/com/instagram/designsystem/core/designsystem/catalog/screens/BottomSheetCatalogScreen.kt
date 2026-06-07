package com.instagram.designsystem.core.designsystem.catalog.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.instagram.designsystem.R
import com.instagram.designsystem.core.designsystem.components.bottomsheet.DSBottomSheet
import com.instagram.designsystem.core.designsystem.components.bottomsheet.SheetSize
import com.instagram.designsystem.core.designsystem.components.bottomsheet.SheetState

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
            Text(stringResource(R.string.launch_big_dialog))
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

        Button(
            onClick = {
                sheets.add(SheetState(id = nextId++, size = SheetSize.Small, depth = 0))
            }
        ) {
            Text(stringResource(R.string.launch_small_dialog))
        }
    }

    // Render active sheets
    sheets.forEachIndexed { index, sheet ->
        DSBottomSheet(
            size = sheet.size,
            depth = sheet.depth,
            onDismiss = {
                sheets.removeAt(index)
            }
        ) {
            Text(
                text = stringResource(R.string.bottom_sheet_text).repeat(50),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_large)))

            Button(
                onClick = {
                    sheets.add(
                        SheetState(
                            id = nextId++,
                            size = SheetSize.Big,
                            depth = if (sheet.size == SheetSize.Big) sheet.depth + 1 else 0
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.launch_big_sheet))
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))

            Button(
                onClick = {
                    sheets.add(
                        SheetState(
                            id = nextId++,
                            size = SheetSize.Small,
                            depth = if (sheet.size == SheetSize.Small) sheet.depth + 1 else 0
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.launch_small_sheet))
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_extra_large)))
        }
    }
}
