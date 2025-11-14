package br.com.spike.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.unit.dp
import br.com.spike.domain.model.User
import br.com.spike.presentation.login.LoginScreen
import br.com.spike.ui.components.SpikeIcon
import br.com.spike.ui.components.SpikeIcons
import br.com.spike.ui.components.SpikeScreen
import br.com.spike.ui.components.SpikeText
import br.com.spike.ui.components.SpikeTopBar
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import org.kodein.di.compose.rememberInstance

data class Profile(val user: User) : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val screenModel by rememberInstance<ProfileScreenModel>()

        SpikeScreen(
            topBar = {
                SpikeTopBar(
                    title = "Perfil",
                    onNavigateBack = navigator::pop
                )
            }
        ) {
            ProfileContent(
                user = user,
                onClickSignOut = {
                    screenModel.signOut()
                    navigator.replaceAll(LoginScreen)
                }
            )
        }
    }
}

@Composable
private fun ProfileContent(
    user: User,
    onClickSignOut: () -> Unit,
) {
    Column {
        Header(user)
        Statistics()
        ProfileButton(
            icon = SpikeIcons.Pen,
            label = "Editar perfil",
            action = {}
        )
        ProfileButton(
            icon = SpikeIcons.SignOut,
            label = "Sair",
            action = onClickSignOut
        )
    }
}

@Composable
private fun Header(user: User) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(all = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(
                    color = SpikeTheme.colors.backgroundBody,
                    shape = RoundedCornerShape(32.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = user.avatarUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            SpikeText(user.name, style = SpikeTheme.typography.titleMedium)
            SpikeText(
                "@matc",
                style = SpikeTheme.typography.labelSmall,
                color = SpikeTheme.colors.contentBrand
            )
        }
    }
}

@Composable
private fun Statistics() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        StatisticCard("Partidas jogadas", "48")
        StatisticCard("Partidas criadas", "32")
    }
}

@Composable
private fun RowScope.StatisticCard(label: String, value: String) {
    Column(
        modifier = Modifier
            .weight(1f)
            .background(
                color = SpikeTheme.colors.backgroundBrandVariant,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        SpikeText(
            label,
            style = SpikeTheme.typography.labelSmall,
            color = SpikeTheme.colors.contentBrand
        )
        SpikeText(value, fontWeight = FontWeight.Black, style = SpikeTheme.typography.bodyLarge)
    }
}

@Composable
private fun ProfileButton(icon: SpikeIcons, label: String, action: () -> Unit) {
    Surface(
        onClick = action,
        color = SpikeTheme.colors.backgroundBody,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(all = 16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SpikeIcon(
                icon = icon,
                tint = SpikeTheme.colors.backgroundBrand,
                modifier = Modifier.width(16.dp)
            )
            SpikeText(label)
        }
    }
}