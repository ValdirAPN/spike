package br.com.spike.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.spike.domain.model.Match
import br.com.spike.ui.components.SpikeIcon
import br.com.spike.ui.components.SpikeIcons
import br.com.spike.ui.components.SpikeMatchCard
import br.com.spike.ui.components.SpikeScreen
import br.com.spike.ui.components.SpikeText
import br.com.spike.ui.components.SpikeTopBar
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.ui.tooling.preview.Preview

object MatchExplorer : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        SpikeScreen(
            topBar = {
                SpikeTopBar(
                    title = "Buscar Partida",
                    onNavigateBack = navigator::pop
                )
            }
        ) {
            MatchExplorerContent(
                matches = emptyList()
            )
        }
    }
}

@Composable
private fun MatchExplorerContent(matches: List<Match>) {
    Column(Modifier.fillMaxSize()) {
        if (matches.isEmpty()) {
            NoMatches()
        } else {
            LazyColumn {
                items(matches) { match ->
                    SpikeMatchCard(match, onClick = {})
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.NoMatches() {
    Column(
        modifier = Modifier.fillMaxWidth().weight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpikeIcon(
            icon = SpikeIcons.Empty,
            modifier = Modifier
                .size(32.dp)
                .padding(bottom = 16.dp)
        )
        SpikeText(
            text = "Nenhuma partida encontrada",
            color = SpikeTheme.colors.contentLow
        )
    }
}

@Composable
@Preview
private fun MatchExplorerContentPreview() {
    SpikeTheme {
        MatchExplorerContent(
            matches = emptyList()
        )
    }
}