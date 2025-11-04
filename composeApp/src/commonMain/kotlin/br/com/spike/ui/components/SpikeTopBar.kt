package br.com.spike.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.spike.ui.theme.SpikeTheme

@Composable
fun SpikeTopBar(
    title: String,
    onNavigateBack: () -> Unit,
    primaryAction: SpikeTopBarTrailing? = null,
    secondaryAction: SpikeTopBarTrailing? = null,
) {
    Box(
        modifier = Modifier
            .statusBarsPadding()
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        SpikeIconButton(
            icon = SpikeIcons.ArrowBack,
            action = onNavigateBack,
        )
        SpikeText(
            text = title,
            style = SpikeTheme.typography.titleSmall,
            modifier = Modifier
                .align(
                    Alignment.Center
                )
        )
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            horizontalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            primaryAction?.let {
                SpikeIconButton(icon = it.icon, action = it.action)
            }
            secondaryAction?.let {
                SpikeIconButton(icon = it.icon, action = it.action)
            }
        }
    }
}

data class SpikeTopBarTrailing(
    val icon: SpikeIcons,
    val action: () -> Unit
)