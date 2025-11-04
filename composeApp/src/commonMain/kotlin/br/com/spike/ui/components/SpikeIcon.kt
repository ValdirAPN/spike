package br.com.spike.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import spike.composeapp.generated.resources.Res
import spike.composeapp.generated.resources.arrow_back
import spike.composeapp.generated.resources.bell
import spike.composeapp.generated.resources.calendar_filled
import spike.composeapp.generated.resources.clock_filled
import spike.composeapp.generated.resources.compass_filled
import spike.composeapp.generated.resources.empty
import spike.composeapp.generated.resources.location_target
import spike.composeapp.generated.resources.pen
import spike.composeapp.generated.resources.plus_filled
import spike.composeapp.generated.resources.share
import spike.composeapp.generated.resources.signout
import spike.composeapp.generated.resources.user
import spike.composeapp.generated.resources.users_four_filled

@Composable
fun SpikeIcon(
    icon: SpikeIcons,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current
) {
    Icon(
        modifier = modifier.width(20.dp).aspectRatio(1f),
        painter = painterResource(icon.res),
        contentDescription = contentDescription,
        tint = tint,
    )
}

enum class SpikeIcons(val res: DrawableResource) {
    User(res = Res.drawable.user),
    Bell(res = Res.drawable.bell),
    Empty(res = Res.drawable.empty),
    ArrowBack(res = Res.drawable.arrow_back),
    Pen(res = Res.drawable.pen),
    Share(res = Res.drawable.share),
    SignOut(res = Res.drawable.signout),
    LocationTarget(res = Res.drawable.location_target),
    UsersFourFilled(res = Res.drawable.users_four_filled),
    CompassFilled(res = Res.drawable.compass_filled),
    PlusFilled(res = Res.drawable.plus_filled),
    ClockFilled(res = Res.drawable.clock_filled),
    CalendarFilled(res = Res.drawable.calendar_filled),
}