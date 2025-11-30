package br.com.spike.presentation.matchDetails

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.spike.domain.model.CourtType
import br.com.spike.domain.model.GenderPreference
import br.com.spike.domain.model.Match
import br.com.spike.domain.model.Player
import br.com.spike.domain.model.SkillLevel
import br.com.spike.domain.model.TeamSize
import br.com.spike.domain.model.Visibility
import br.com.spike.domain.utils.formatWithDuration
import br.com.spike.domain.utils.toDayOfWeekDayOfMonthAndMonthName
import br.com.spike.presentation.PtStrings
import br.com.spike.presentation.Strings
import br.com.spike.ui.components.SpikeButton
import br.com.spike.ui.components.SpikeIcon
import br.com.spike.ui.components.SpikeIcons
import br.com.spike.ui.components.SpikeProgress
import br.com.spike.ui.components.SpikeScreen
import br.com.spike.ui.components.SpikeText
import br.com.spike.ui.components.SpikeTopBar
import br.com.spike.ui.components.SpikeTopBarTrailing
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

data class MatchDetailsScreen(val match: Match) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val strings = LocalStrings.current
        val screenModel = rememberScreenModel<MatchDetailsScreenModel>()
        val state by screenModel.state.collectAsStateWithLifecycle()

        val handleIntent: (MatchDetailsIntent) -> Unit = remember {
            { intent ->
                screenModel.handleIntent(intent)
            }
        }

        LaunchedEffect(Unit) {
            handleIntent(MatchDetailsIntent.AssembleData(match))
        }

        MatchDetailsContent(
            state = state,
            strings = strings,
            onNavigateBack = navigator::pop
        )
    }
}

@Composable
private fun MatchDetailsContent(
    state: MatchDetailsState,
    strings: Strings,
    onNavigateBack: () -> Unit,
) = with(state) {
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
        if (match != null) {
            Column(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.weight(1f)) {
                    SpikeText(
                        text = match.title.uppercase(),
                        style = SpikeTheme.typography.headlineMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                    DateAndTimeCard(
                        date = match.startAt.date.toDayOfWeekDayOfMonthAndMonthName(
                            daysOfWeekNames = strings.daysOfWeekNames,
                            monthNames = strings.monthNames
                        ),
                        time = match.startAt.formatWithDuration(match.durationMinutes)
                    )
                    LocationCard()
                    Attributes(
                        strings = strings,
                        teamSize = match.teamSize,
                        courtType = match.courtType,
                        genderPreference = match.genderPreference,
                        skillLevel = match.skillLevel,
                    )
                    match.organizer?.let {
                        OrganizerCard(organizer = it)
                    }
                    PlayersCard(users = match.players)
                }
                Box(Modifier.padding(all = 16.dp)) {
                    SpikeButton("Participar", action = {})
                }
            }
        } else {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                SpikeProgress()
            }
        }
    }
}

@Composable
private fun DateAndTimeCard(
    date: String,
    time: String,
) {
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
            SpikeText(date)
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
            SpikeText(time)
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
private fun Attributes(
    strings: Strings,
    teamSize: TeamSize,
    skillLevel: SkillLevel,
    genderPreference: GenderPreference,
    courtType: CourtType
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AttributeCard(
                "Nível",
                strings.skillLevel(skillLevel)
            )
            AttributeCard(
                "Gênero",
                strings.genderPreference(genderPreference)
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            AttributeCard(
                "Quadra",
                strings.courtType(courtType)
            )
            AttributeCard(
                "Times",
                strings.teamSize(teamSize)
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
private fun OrganizerCard(organizer: Player) {
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
                organizer.username.uppercase(),
                fontWeight = FontWeight.Black,
                style = SpikeTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun PlayersCard(users: List<Player>) {
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
                    text = player.username,
                )
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
@Preview
private fun MatchDetailsContentPreview() {
    SpikeTheme {
        MatchDetailsContent(
            state = MatchDetailsState(
                match = Match(
                    id = "",
                    title = "Vôlei no Ninho",
                    spots = 18,
                    players = emptyList(),
                    teamSize = TeamSize.SIX_V_SIX,
                    courtType = CourtType.BEACH,
                    skillLevel = SkillLevel.BEGINNER,
                    genderPreference = GenderPreference.MIXED,
                    visibility = Visibility.PUBLIC,
                    startAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                    durationMinutes = 90,
                    organizer = Player(
                        uid = "",
                        username = "Matheus Carlos",
                        avatarUrl = ""
                    )
                ),
            ),
            strings = PtStrings,
            onNavigateBack = {}
        )
    }
}