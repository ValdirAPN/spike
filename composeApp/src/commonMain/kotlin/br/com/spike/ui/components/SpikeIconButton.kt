package br.com.spike.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.spike.ui.theme.SpikeTheme

@Composable
fun SpikeIconButton(
    icon: SpikeIcons,
    action: () -> Unit,
    modifier: Modifier = Modifier,
    size: SpikeIconButtonSize = SpikeIconButtonSize.Small
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = SpikeTheme.colors.backgroundBody,
        contentColor = SpikeTheme.colors.contentHigh,
        onClick = action,
    ) {
        Box(modifier = Modifier.size(size.butonSize), contentAlignment = Alignment.Center) {
            SpikeIcon(
                icon = icon,
                modifier = Modifier.width(size.iconWidth)
            )
        }
    }
}

enum class SpikeIconButtonSize(
    val butonSize: Dp,
    val iconWidth: Dp,
) {
    Small(
        butonSize = 42.dp,
        iconWidth = 16.dp
    ),
    Medium(
        butonSize = 48.dp,
        iconWidth = 20.dp
    ),
    Large(
        butonSize = 52.dp,
        iconWidth = 22.dp
    )
}