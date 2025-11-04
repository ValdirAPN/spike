package br.com.spike.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.spike.ui.theme.SpikeTheme

@Composable
fun SpikeButton(
    text: String,
    action: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        contentColor = SpikeTheme.colors.contentOnColorHigh,
        color = SpikeTheme.colors.backgroundBrand,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier,
        onClick = action
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            contentAlignment = Alignment.Center
        ) {
            SpikeText(
                text = text,
                style = SpikeTheme.typography.labelMedium
            )
        }
    }
}