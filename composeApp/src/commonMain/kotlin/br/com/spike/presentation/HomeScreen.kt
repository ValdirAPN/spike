package br.com.spike.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.spike.ui.components.SpikeIcon
import br.com.spike.ui.components.SpikeIconButton
import br.com.spike.ui.components.SpikeIcons
import br.com.spike.ui.components.SpikeScreen
import br.com.spike.ui.components.SpikeText
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.ui.tooling.preview.Preview

object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        HomeContent(
            onClickCreateMatchButton = { navigator.push(MatchForm) }
        )
    }
}

@Composable
private fun HomeContent(
    onClickCreateMatchButton: () -> Unit
) {
    SpikeScreen {
        Header()
        HomeActions(
            onClickCreateMatchButton = onClickCreateMatchButton
        )
        NextMatches()
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SpikeIconButton(
            icon = SpikeIcons.User,
            action = {},
        )
        SpikeText(
            text = "Mateus Carlos",
            modifier = Modifier.weight(1f),
            style = SpikeTheme.typography.bodyLarge
        )
        SpikeIconButton(
            icon = SpikeIcons.Bell,
            action = {},
        )
    }
}

@Composable
private fun HomeActions(
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
            onClick = {}
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
private fun NextMatches() {
    Column(modifier = Modifier.padding(16.dp)) {
        SpikeText(
            text = "Próximas Partidas",
            style = SpikeTheme.typography.titleSmall
        )
        NoMatches()
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
            onClickCreateMatchButton = {}
        )
    }
}