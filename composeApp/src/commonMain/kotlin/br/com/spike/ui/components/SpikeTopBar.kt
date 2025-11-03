package br.com.spike.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

@Composable
fun SpikeTopBar(
    title: String,
    navigator: Navigator = LocalNavigator.currentOrThrow
) {
    Box(
        modifier = Modifier
            .statusBarsPadding()
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        SpikeIconButton(
            icon = SpikeIcons.ArrowBack,
            action = navigator::pop,
        )
        SpikeText(
            text = title,
            style = SpikeTheme.typography.titleSmall,
            modifier = Modifier.align(
                Alignment.Center
            )
        )
    }
}