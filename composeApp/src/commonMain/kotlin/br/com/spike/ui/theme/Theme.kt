package br.com.spike.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun SpikeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalSpikeColorScheme provides darkColors,
        LocalSpikeTypographyProvider provides spikeTypography()
    ) {
        content()
    }
}

object SpikeTheme {
    val colors: SpikeColorScheme
        @Composable get() = LocalSpikeColorScheme.current

    val typography: Typography
        @Composable get() = LocalSpikeTypographyProvider.current
}