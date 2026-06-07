package com.instagram.designsystem.core.designsystem.components.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import com.instagram.designsystem.R
import kotlinx.coroutines.launch

enum class SheetSize {
    Big, Small
}

data class SheetState(
    val id: Int,
    val size: SheetSize,
    val depth: Int = 0,
)

/**
 * A modal bottom sheet that supports different sizes and depth-based stacking.
 *
 * @param size The [SheetSize] of the bottom sheet (Big or Small).
 * @param depth The current stacking depth, used to calculate offsets for multiple open sheets.
 * @param onDismiss Callback invoked when the sheet should be dismissed.
 * @param title Optional title text. If null, a default title based on size and depth is shown.
 * Pass an empty string to hide the title completely.
 * @param content The composable content to be displayed within the bottom sheet.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DSBottomSheet(
    size: SheetSize,
    depth: Int,
    onDismiss: () -> Unit,
    title: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = size == SheetSize.Big
    )
    val scope = rememberCoroutineScope()
    val depthDescription = stringResource(R.string.stack_depth, depth)
    
    val displayTitle = title ?: if (size == SheetSize.Big) {
        stringResource(R.string.big_bottom_sheet_title, depth)
    } else {
        stringResource(R.string.small_bottom_sheet_title, depth)
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        dragHandle = if (size == SheetSize.Small) { { /* Default drag handle for small */ } } else null,
        contentWindowInsets = {
            if (size == SheetSize.Big) BottomSheetDefaults.windowInsets else WindowInsets(0, 0, 0, 0)
        },
        modifier = (if (size == SheetSize.Big) {
            Modifier
                .fillMaxHeight()
                .padding(top = dimensionResource(R.dimen.bottom_sheet_depth_big_offset) * depth)
        } else {
            Modifier
        }).semantics {
            stateDescription = depthDescription
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (size == SheetSize.Big) Modifier.fillMaxHeight() else Modifier
                )
                .then(
                    if (size == SheetSize.Small) Modifier.height(dimensionResource(R.dimen.bottom_sheet_small_height) - (dimensionResource(R.dimen.bottom_sheet_depth_small_offset) * depth)) else Modifier
                )
                .padding(dimensionResource(R.dimen.padding_medium))
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (displayTitle.isNotEmpty()) {
                    Text(
                        text = displayTitle,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
                IconButton(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onDismiss()
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close)
                    )
                }
            }

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
            
            content()
        }
    }
}
