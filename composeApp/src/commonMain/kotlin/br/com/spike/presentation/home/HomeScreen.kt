package br.com.spike.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.spike.domain.model.Match
import br.com.spike.domain.model.Player
import br.com.spike.presentation.MatchDetails
import br.com.spike.presentation.MatchExplorer
import br.com.spike.presentation.MatchForm
import br.com.spike.presentation.Profile
import br.com.spike.ui.components.SpikeIcon
import br.com.spike.ui.components.SpikeIconButton
import br.com.spike.ui.components.SpikeIconButtonSize
import br.com.spike.ui.components.SpikeIcons
import br.com.spike.ui.components.SpikeMatchCard
import br.com.spike.ui.components.SpikeScreen
import br.com.spike.ui.components.SpikeText
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview

object HomeScreen : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { HomeScreenModel() }
        val state by screenModel.state.collectAsStateWithLifecycle()

        HomeContent(
            state = state,
            onClickProfile = {
                navigator.push(
                    Profile(
                        Player(
                            id = "",
                            name = state.username,
                            avatarUrl = state.avatarUrl
                        )
                    )
                )
            },
            onClickFindMatchButton = { navigator.push(MatchExplorer) },
            onClickCreateMatchButton = { navigator.push(MatchForm) },
            onClickMatch = { match -> navigator.push(MatchDetails(match)) }
        )
    }
}

@Composable
private fun HomeContent(
    state: HomeScreenState,
    onClickProfile: () -> Unit,
    onClickFindMatchButton: () -> Unit,
    onClickCreateMatchButton: () -> Unit,
    onClickMatch: (Match) -> Unit,
) {
    SpikeScreen {
        Header(
            state = state,
            onClickProfile = onClickProfile
        )
        HomeActions(
            onClickFindMatchButton = onClickFindMatchButton,
            onClickCreateMatchButton = onClickCreateMatchButton
        )
        UpcomingMatches(
            matches = state.upcomingMatches,
            onClickMatch = onClickMatch
        )
    }
}

@Composable
private fun Header(
    state: HomeScreenState,
    onClickProfile: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = SpikeTheme.colors.backgroundBody,
            contentColor = SpikeTheme.colors.contentHigh,
            onClick = onClickProfile,
        ) {
            Box(modifier = Modifier.size(48.dp), contentAlignment = Alignment.Center) {
                AsyncImage(
                    model = state.avatarUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        }
        SpikeText(
            text = state.username,
            modifier = Modifier.weight(1f),
            style = SpikeTheme.typography.titleMedium
        )
        SpikeIconButton(
            icon = SpikeIcons.Bell,
            action = {},
            size = SpikeIconButtonSize.Medium
        )
    }
}

@Composable
private fun HomeActions(
    onClickFindMatchButton: () -> Unit,
    onClickCreateMatchButton: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HomeActionButton(
            text = "Buscar partidas",
            icon = SpikeIcons.CompassFilled,
            highlight = false,
            onClick = onClickFindMatchButton
        )
        HomeActionButton(
            text = "Criar partida",
            icon = SpikeIcons.PlusFilled,
            highlight = true,
            onClick = onClickCreateMatchButton
        )
    }
}

@Composable
private fun UpcomingMatches(
    matches: List<Match>,
    onClickMatch: (Match) -> Unit,
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SpikeText(
            text = "Próximas Partidas",
            style = SpikeTheme.typography.titleSmall
        )
        if (matches.isEmpty()) {
            NoMatches()
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = matches) { match ->
                    SpikeMatchCard(
                        match = match,
                        onClick = { onClickMatch(match) }
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.HomeActionButton(
    text: String,
    icon: SpikeIcons,
    highlight: Boolean,
    onClick: () -> Unit,
) {
    val (color, contentColor) = if (highlight) {
        SpikeTheme.colors.backgroundBrand to SpikeTheme.colors.contentOnColorHigh
    } else SpikeTheme.colors.backgroundBody to SpikeTheme.colors.contentBrand

    Surface(
        modifier = Modifier.weight(weight = 1f),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = color,
        contentColor = contentColor,
    ) {
        Column(
            modifier = Modifier
                .weight(weight = 1f)
                .height(height = 124.dp)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            SpikeIcon(icon = icon, modifier = Modifier.size(24.dp))
            SpikeText(text = text)
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
            text = "Nenhuma partida marcada",
            color = SpikeTheme.colors.contentLow
        )
    }
}

@Composable
@Preview
private fun HomeContentPreview() {
    SpikeTheme {
        HomeContent(
            state = HomeScreenState(),
            onClickProfile = {},
            onClickFindMatchButton = {},
            onClickCreateMatchButton = {},
            onClickMatch = {}
        )
    }
}