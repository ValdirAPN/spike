package br.com.spike.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
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
import br.com.spike.ui.theme.SpikeTheme
import coil3.compose.AsyncImage
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.ExperimentalTime

@Composable
fun SpikeMatchCard(
    match: Match,
    strings: Strings,
    onClick: () -> Unit
) = with(match) {
    Surface(
        color = SpikeTheme.colors.backgroundBrandVariant,
        contentColor = SpikeTheme.colors.contentHigh,
        shape = RoundedCornerShape(16.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SpikeText(
                text = title.uppercase(),
                style = SpikeTheme.typography.titleSmall.copy(fontWeight = FontWeight.Black)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DateAndTime(
                    startAt = startAt,
                    duration = durationMinutes,
                    daysOfWeekNames = strings.daysOfWeekNames,
                    monthNames = strings.monthNames
                )
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    InfoLabel(strings.skillLevel(skillLevel))
                    InfoLabel(strings.genderPreference(genderPreference))
                    InfoLabel(strings.courtType(courtType))
                    InfoLabel(strings.teamSize(teamSize))
                }
            }
            Footer(
                players = players,
                spots = spots,
            )
        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
private fun DateAndTime(
    startAt: LocalDateTime,
    duration: Int,
    daysOfWeekNames: DayOfWeekNames,
    monthNames: MonthNames,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                SpikeTheme.colors.backgroundSurface.copy(alpha = .16f),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(all = 12.dp),
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
            SpikeText(startAt.date.toDayOfWeekDayOfMonthAndMonthName(daysOfWeekNames, monthNames))
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
            SpikeText(startAt.formatWithDuration(duration))
        }
    }
}

@Composable
private fun InfoLabel(
    text: String
) {
    Box(
        modifier = Modifier
            .background(SpikeTheme.colors.backgroundBrand, shape = RoundedCornerShape(100))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        SpikeText(
            text,
            color = SpikeTheme.colors.contentOnColorHigh,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            style = SpikeTheme.typography.labelSmall
        )
    }
}

@Composable
private fun Footer(
    players: List<Player>,
    spots: Int,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        SpikeText("${players.size}", fontWeight = FontWeight.Bold)
        SpikeText(
            "/$spots",
            fontWeight = FontWeight.Bold,
            color = SpikeTheme.colors.contentHigh.copy(alpha = .5f)
        )
        Spacer(Modifier.width(8.dp))
        Row {
            players.take(5).forEach { player ->
                Box(Modifier.size(24.dp).clip(RoundedCornerShape(100))) {
                    AsyncImage(
                        modifier = Modifier.matchParentSize(),
                        model = player.avatarUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
@Preview
fun SpikeMatchCardPreview() {
    SpikeTheme {
        SpikeMatchCard(
            match = Match(
                id = "",
                title = "Vôlei no Ninho",
                spots = 18,
                players = emptyList(),
                teamSize = TeamSize.TWO_V_TWO,
                courtType = CourtType.BEACH,
                skillLevel = SkillLevel.BEGINNER,
                genderPreference = GenderPreference.MIXED,
                visibility = Visibility.PUBLIC,
                startAt = LocalDateTime(year = 2025, month = 9, day = 30, hour = 15, minute = 30),
                durationMinutes = 90,
                organizer = Player(
                    uid = "",
                    username = "Matheus Carlos",
                    avatarUrl = ""
                )
            ),
            strings = PtStrings,
            onClick = {}
        )
    }
}