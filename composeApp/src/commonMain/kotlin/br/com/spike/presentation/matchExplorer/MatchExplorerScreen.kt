package br.com.spike.presentation.matchExplorer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.spike.domain.model.Match
import br.com.spike.presentation.MatchDetailsScreen
import br.com.spike.presentation.PtStrings
import br.com.spike.presentation.Strings
import br.com.spike.ui.components.SpikeIcon
import br.com.spike.ui.components.SpikeIcons
import br.com.spike.ui.components.SpikeMatchCard
import br.com.spike.ui.components.SpikeProgress
import br.com.spike.ui.components.SpikeScreen
import br.com.spike.ui.components.SpikeText
import br.com.spike.ui.components.SpikeTopBar
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.ui.tooling.preview.Preview

object MatchExplorer : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val strings = LocalStrings.current
        val screenModel = rememberScreenModel<MatchExplorerScreenModel>()
        val state by screenModel.state.collectAsStateWithLifecycle()

        val loadMatches = remember { { screenModel.loadMatches() } }

        LaunchedEffect(Unit) {
            loadMatches()
        }

        SpikeScreen(
            topBar = {
                SpikeTopBar(
                    title = "Buscar Partida",
                    onNavigateBack = navigator::pop
                )
            }
        ) {
            MatchExplorerContent(
                state = state,
                strings = strings,
                onClickMatch = { match -> navigator.push(MatchDetailsScreen(match)) }
            )
        }
    }
}

@Composable
private fun MatchExplorerContent(
    state: MatchExplorerState,
    strings: Strings,
    onClickMatch: (Match) -> Unit,
) = with(state) {
    Column(Modifier.fillMaxSize()) {
        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                SpikeProgress()
            }
            return@Column
        }
        if (matches.isEmpty()) {
            NoMatches()
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(matches) { match ->
                    SpikeMatchCard(
                        match = match,
                        strings = strings,
                        onClick = { onClickMatch(match) }
                    )
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
            state = MatchExplorerState(),
            strings = PtStrings,
            onClickMatch = {}
        )
    }
}