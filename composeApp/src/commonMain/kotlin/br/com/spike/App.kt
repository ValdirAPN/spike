package br.com.spike

import androidx.compose.runtime.Composable
import br.com.spike.presentation.splash.SplashScreen
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kodein.di.compose.subDI

@Composable
@Preview
fun App() {
    subDI(
        parentDI = appModule,
        diBuilder = {}
    ) {
        SpikeTheme {
            Navigator(SplashScreen) { navigator ->
                SlideTransition(
                    navigator = navigator,
                )
            }
        }
    }
}