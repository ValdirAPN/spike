package br.com.spike.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.spike.ui.theme.SpikeTheme

@Composable
fun SpikeProgress(
    modifier: Modifier = Modifier,
    size: SpikeProgressSize = SpikeProgressSize.Large,
    color: Color = SpikeTheme.colors.backgroundBrand
) {
    val (sizeDp, strokeWidth) = when (size) {
        SpikeProgressSize.Small -> 12.dp to 3.dp
        SpikeProgressSize.Medium -> 32.dp to 4.dp
        SpikeProgressSize.Large -> 48.dp to 5.dp
    }
    CircularProgressIndicator(
        modifier = modifier.size(sizeDp),
        color = color,
        strokeWidth = strokeWidth
    )
}

enum class SpikeProgressSize {
    Small,
    Medium,
    Large
}