package br.com.spike.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import spike.composeapp.generated.resources.Outfit_Black
import spike.composeapp.generated.resources.Outfit_Bold
import spike.composeapp.generated.resources.Outfit_Medium
import spike.composeapp.generated.resources.Outfit_Regular
import spike.composeapp.generated.resources.Outfit_SemiBold
import spike.composeapp.generated.resources.Res

@Composable
fun outfitFontFamily() = FontFamily(
    Font(Res.font.Outfit_Regular, weight = FontWeight.Normal),
    Font(Res.font.Outfit_Medium, weight = FontWeight.Medium),
    Font(Res.font.Outfit_SemiBold, weight = FontWeight.SemiBold),
    Font(Res.font.Outfit_Bold, weight = FontWeight.Bold),
    Font(Res.font.Outfit_Black, weight = FontWeight.Black),
)

@Composable
fun spikeTypography() = Typography(
    bodyMedium = TextStyle(
        fontFamily = outfitFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),
)

internal val LocalSpikeTypographyProvider =
    staticCompositionLocalOf<Typography> { error("Typography not provided.") }