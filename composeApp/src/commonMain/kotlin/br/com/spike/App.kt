package br.com.spike

import androidx.compose.runtime.Composable
import br.com.spike.presentation.home.HomeScreen
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    SpikeTheme {
        Navigator(HomeScreen) { navigator ->
            SlideTransition(
                navigator = navigator,
            )
        }
    }
}