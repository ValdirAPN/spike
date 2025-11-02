package br.com.spike.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.spike.ui.theme.SpikeTheme

@Composable
fun SpikeIconButton(
    icon: SpikeIcons,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = SpikeTheme.colors.backgroundBody,
        contentColor = SpikeTheme.colors.contentHigh,
        onClick = action,
    ) {
        Box(modifier = Modifier.size(48.dp), contentAlignment = Alignment.Center) {
            SpikeIcon(icon)
        }
    }
}