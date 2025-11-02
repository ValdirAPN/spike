package br.com.spike.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import spike.composeapp.generated.resources.Res
import spike.composeapp.generated.resources.bell
import spike.composeapp.generated.resources.compass_filled
import spike.composeapp.generated.resources.empty
import spike.composeapp.generated.resources.plus_filled
import spike.composeapp.generated.resources.user

@Composable
fun SpikeIcon(
    icon: SpikeIcons,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Icon(
        modifier = modifier.width(20.dp).aspectRatio(1f),
        painter = painterResource(icon.res),
        contentDescription = contentDescription
    )
}

enum class SpikeIcons(val res: DrawableResource) {
    User(res = Res.drawable.user),
    Bell(res = Res.drawable.bell),
    Empty(res = Res.drawable.empty),
    CompassFilled(res = Res.drawable.compass_filled),
    PlusFilled(res = Res.drawable.plus_filled),
}