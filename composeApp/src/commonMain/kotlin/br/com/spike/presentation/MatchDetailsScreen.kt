package br.com.spike.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.spike.domain.model.Match
import br.com.spike.domain.model.User
import br.com.spike.ui.components.SpikeButton
import br.com.spike.ui.components.SpikeIcon
import br.com.spike.ui.components.SpikeIcons
import br.com.spike.ui.components.SpikeScreen
import br.com.spike.ui.components.SpikeText
import br.com.spike.ui.components.SpikeTopBar
import br.com.spike.ui.components.SpikeTopBarTrailing
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import org.jetbrains.compose.ui.tooling.preview.Preview

data class MatchDetails(val match: Match) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        MatchDetailsContent(
            match = match,
            onNavigateBack = navigator::pop
        )
    }
}

@Composable
private fun MatchDetailsContent(
    match: Match,
    onNavigateBack: () -> Unit,
) {
    SpikeScreen(
        topBar = {
            SpikeTopBar(
                title = "Detalhes da Partida",
                onNavigateBack = onNavigateBack,
                primaryAction = SpikeTopBarTrailing(
                    icon = SpikeIcons.Share,
                    action = {}
                ),
                secondaryAction = SpikeTopBarTrailing(
                    icon = SpikeIcons.Pen,
                    action = {}
                )
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.weight(1f)) {
                SpikeText(
                    text = match.title.uppercase(),
                    style = SpikeTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                )
                DateAndTimeCard()
                LocationCard()
                Attributes()
                OrganizerCard(organizer = match.organizer)
                PlayersCard(users = match.users)
            }
            Box(Modifier.padding(all = 16.dp)) {
                SpikeButton("Participar", action = {})
            }
        }
    }
}

@Composable
private fun DateAndTimeCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .background(
                SpikeTheme.colors.backgroundBody,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(all = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SpikeIcon(
                icon = SpikeIcons.CalendarFilled,
                tint = SpikeTheme.colors.contentBrand,
                modifier = Modifier.width(14.dp),
            )
            SpikeText("Sábado, 19 de Outubro")
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SpikeIcon(
                icon = SpikeIcons.ClockFilled,
                tint = SpikeTheme.colors.contentBrand,
                modifier = Modifier.width(14.dp),
            )
            SpikeText("19:00 - 21:00")
        }
    }
}

@Composable
private fun LocationCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .background(
                SpikeTheme.colors.backgroundBrandVariant,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SpikeIcon(
                icon = SpikeIcons.LocationTarget,
                tint = SpikeTheme.colors.contentBrand,
                modifier = Modifier.width(14.dp)
            )
            SpikeText(
                "Localização".uppercase(),
                color = SpikeTheme.colors.contentBrand,
                style = SpikeTheme.typography.labelSmall.copy(fontWeight = FontWeight.Black)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            SpikeText("Ninho Residencial", fontWeight = FontWeight.Bold)
            SpikeText(
                "R. Francisco Mota, 4222 - Rincão, Mossoró - RN, 59626-105",
                style = SpikeTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun Attributes() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AttributeCard(
                "Nível",
                "Iniciante"
            )
            AttributeCard(
                "Gênero",
                "Misto"
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AttributeCard(
                "Quadra",
                "Poliesportiva"
            )
            AttributeCard(
                "Times",
                "6x6"
            )
        }
    }
}

@Composable
private fun RowScope.AttributeCard(label: String, value: String) {
    Column(
        modifier = Modifier
            .weight(1f)
            .background(
                SpikeTheme.colors.backgroundBody,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SpikeText(
            text = label,
            style = SpikeTheme.typography.labelSmall,
            color = SpikeTheme.colors.contentBrand
        )
        SpikeText(value.uppercase(), fontWeight = FontWeight.Black)
    }
}

@Composable
private fun OrganizerCard(organizer: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .background(
                color = SpikeTheme.colors.backgroundBrandVariant,
                shape = RoundedCornerShape(size = 16.dp)
            )
            .padding(all = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(100))
                .background(
                    SpikeTheme.colors.backgroundSurface.copy(alpha = .2f),
                    shape = RoundedCornerShape(100)
                ),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = organizer.avatarUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            SpikeText(
                text = "Organizador",
                style = SpikeTheme.typography.labelSmall,
                color = SpikeTheme.colors.contentBrand
            )
            SpikeText(
                organizer.name.uppercase(),
                fontWeight = FontWeight.Black,
                style = SpikeTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun PlayersCard(users: List<User>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .background(SpikeTheme.colors.backgroundBody, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Row(
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SpikeIcon(
                icon = SpikeIcons.UsersFourFilled,
                tint = SpikeTheme.colors.contentBrand,
                modifier = Modifier.width(14.dp)
            )
            SpikeText(
                "Jogadores".uppercase(),
                color = SpikeTheme.colors.contentBrand,
                style = SpikeTheme.typography.labelSmall.copy(fontWeight = FontWeight.Black)
            )
        }
        users.forEach { player ->
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(100))
                        .background(
                            SpikeTheme.colors.backgroundSurface.copy(alpha = .2f),
                            shape = RoundedCornerShape(100)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = player.avatarUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
                SpikeText(
                    text = player.name,
                )
            }
        }
    }
}

@Composable
@Preview
private fun MatchDetailsContentPreview() {
    SpikeTheme {
        MatchDetailsContent(
            match = Match(
                id = "",
                title = "Vôlei no Ninho",
                spots = 18,
                users = emptyList(),
                organizer = User(id = "", name = "Matheus Carlos", avatarUrl = "")
            ),
            onNavigateBack = {}
        )
    }
}