package br.com.spike.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.spike.ui.theme.SpikeTheme

@Composable
fun SpikeButton(
    label: String,
    action: () -> Unit,
    modifier: Modifier = Modifier,
    state: SpikeButtonState = SpikeButtonState.Default,
    variant: SpikeButtonVariant = SpikeButtonVariant.PrimarySolid,
) {
    val (color, contentColor) = handleButtonColors(state = state, variant = variant)

    Surface(
        contentColor = contentColor,
        color = color,
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
                text = label,
                style = SpikeTheme.typography.labelMedium
            )
        }
    }
}

@Composable
private fun handleButtonColors(
    state: SpikeButtonState,
    variant: SpikeButtonVariant
): Pair<Color, Color> {
    val colors = when (variant) {
        SpikeButtonVariant.PrimarySolid -> SpikeTheme.colors.backgroundBrand to SpikeTheme.colors.contentOnColorHigh
        SpikeButtonVariant.NeutralSubtle -> SpikeTheme.colors.backgroundBody to SpikeTheme.colors.contentHigh
    }

    return if (state != SpikeButtonState.Default) {
        colors.run { this.first.copy(alpha = .5f) to this.second.copy(alpha = .5f) }
    } else colors
}

enum class SpikeButtonVariant {
    PrimarySolid,
    NeutralSubtle
}

enum class SpikeButtonState {
    Default,
    Disabled,
    Loading
}