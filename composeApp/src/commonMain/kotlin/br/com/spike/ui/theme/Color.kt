package br.com.spike.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val White = Color(0xFFFFFFFF)
val Black950 = Color(0xFF0A0C10)
val Black800 = Color(0xFF141921)
val Green400 = Color(0xFFEFFF00)
val Purple500 = Color(0xFF2E25F6)

data class SpikeColorScheme(
    // Content
    val contentHigh: Color,
    val contentLow: Color,
    val contentOnColorHigh: Color,
    // Background
    val backgroundSurface: Color,
    val backgroundBody: Color,
    val backgroundBrand: Color,
    val backgroundBrandVariant: Color,
)

val darkColors = SpikeColorScheme(
    contentHigh = White,
    contentLow = White.copy(alpha = 0.5f),
    contentOnColorHigh = Black950,
    backgroundSurface = Black950,
    backgroundBody = Black800,
    backgroundBrand = Green400,
    backgroundBrandVariant = Purple500
)

internal val LocalSpikeColorScheme =
    staticCompositionLocalOf<SpikeColorScheme> { error("Colors not provided.") }