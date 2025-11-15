package br.com.spike.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.spike.domain.model.Match
import br.com.spike.domain.model.User
import br.com.spike.ui.theme.SpikeTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SpikeMatchCard(match: Match, onClick: () -> Unit) = with(match) {
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
                DateAndTime()
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    InfoLabel("Iniciante")
                    InfoLabel("Misto")
                    InfoLabel("Quadra")
                }
            }
            Footer(
                playersQuantity = users.size,
                spots = spots,
            )
        }
    }
}

@Composable
private fun DateAndTime() {
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
private fun RowScope.InfoLabel(
    text: String
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .background(SpikeTheme.colors.backgroundBrand, shape = RoundedCornerShape(8.dp))
            .padding(all = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        SpikeText(
            text,
            color = SpikeTheme.colors.contentOnColorHigh,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Footer(
    playersQuantity: Int,
    spots: Int,
) {
    Row {
        SpikeText("$playersQuantity", fontWeight = FontWeight.Bold)
        SpikeText(
            "/$spots",
            fontWeight = FontWeight.Bold,
            color = SpikeTheme.colors.contentHigh.copy(alpha = .5f)
        )
    }
}

@Composable
@Preview
fun SpikeMatchCardPreview() {
    SpikeTheme {
        SpikeMatchCard(
            match = Match(
                id = "",
                title = "Vôlei no Ninho",
                spots = 18,
                users = emptyList(),
                organizer = User(
                    id = "",
                    name = "Matheus Carlos",
                    username = "matc",
                    avatarUrl = ""
                )
            ),
            onClick = {}
        )
    }
}