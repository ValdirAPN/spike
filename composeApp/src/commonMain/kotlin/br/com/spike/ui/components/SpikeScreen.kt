package br.com.spike.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.spike.ui.theme.SpikeTheme

@Composable
fun SpikeScreen(
    topBar: @Composable (() -> Unit) = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        topBar = topBar,
        containerColor = SpikeTheme.colors.backgroundSurface,
        contentColor = SpikeTheme.colors.contentHigh
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            content()
        }
    }
}