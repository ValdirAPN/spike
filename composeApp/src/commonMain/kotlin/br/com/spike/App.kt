package br.com.spike

import androidx.compose.runtime.Composable
import br.com.spike.presentation.HomeScreen
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    SpikeTheme {
        Navigator(HomeScreen)
    }
}