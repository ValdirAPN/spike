package br.com.spike.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import br.com.spike.ui.theme.SpikeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpikeModalBottomSheet(
    onDismissRequest: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        content = content,
        containerColor = SpikeTheme.colors.backgroundBody,
        contentColor = SpikeTheme.colors.contentHigh
    )
}