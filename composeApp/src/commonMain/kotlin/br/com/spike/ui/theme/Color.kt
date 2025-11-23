package br.com.spike.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val White = Color(0xFFFFFFFF)
val Black950 = Color(0xFF0A0C10)
val Black800 = Color(0xFF141921)
val Green500 = Color(0xFFEFFF00)
val Purple500 = Color(0xFF2E25F6)
val Red400 = Color(0xFFFD4649)
val Red900 = Color(0xFF280001)

data class SpikeColorScheme(
    // Content
    val contentHigh: Color,
    val contentLow: Color,
    val contentOnColorHigh: Color,
    val contentBrand: Color,
    val contentNegative: Color,
    // Background
    val backgroundSurface: Color,
    val backgroundBody: Color,
    val backgroundBrand: Color,
    val backgroundBrandVariant: Color,
    val backgroundNegativeSolid: Color,
    val backgroundNegativeSubtle: Color,
    // Border
    val borderNeutral: Color,
    val borderBrand: Color,
    val borderNegative: Color,
)

val darkColors = SpikeColorScheme(
    // Content
    contentHigh = White,
    contentLow = White.copy(alpha = 0.5f),
    contentOnColorHigh = Black950,
    contentBrand = Green500,
    contentNegative = Red400,
    // Background
    backgroundSurface = Black950,
    backgroundBody = Black800,
    backgroundBrand = Green500,
    backgroundBrandVariant = Purple500,
    backgroundNegativeSolid = Red400,
    backgroundNegativeSubtle = Red900,
    // Border
    borderNeutral = Black800,
    borderBrand = Green500,
    borderNegative = Red400,
)

internal val LocalSpikeColorScheme =
    staticCompositionLocalOf<SpikeColorScheme> { error("Colors not provided.") }