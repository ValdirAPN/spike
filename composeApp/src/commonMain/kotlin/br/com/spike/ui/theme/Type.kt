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
    headlineMedium = TextStyle(
        fontFamily = outfitFontFamily(),
        fontWeight = FontWeight.Black,
        fontSize = 24.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = outfitFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        fontFamily = outfitFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = outfitFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = outfitFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        fontFamily = outfitFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),
    labelMedium = TextStyle(
        fontFamily = outfitFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = outfitFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.sp
    ),
)

internal val LocalSpikeTypographyProvider =
    staticCompositionLocalOf<Typography> { error("Typography not provided.") }