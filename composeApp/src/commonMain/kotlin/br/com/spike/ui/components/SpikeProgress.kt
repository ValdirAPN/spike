package br.com.spike.ui.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.spike.ui.theme.SpikeTheme

@Composable
fun SpikeProgress(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier,
        color = SpikeTheme.colors.backgroundBrand,
    )
}