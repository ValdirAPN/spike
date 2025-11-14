package br.com.spike.presentation.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.spike.presentation.login.LoginScreen
import br.com.spike.presentation.home.HomeScreen
import br.com.spike.ui.components.SpikeProgress
import br.com.spike.ui.components.SpikeScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.collectLatest
import org.kodein.di.compose.rememberInstance

object SplashScreen : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val screenModel by rememberInstance<SplashScreenModel>()

        LaunchedEffect(Unit) {
            screenModel.checkAuthState()
            screenModel.events.collectLatest { event ->
                when (event) {
                    is SplashScreenEvent.Authenticated -> navigator.replaceAll(HomeScreen)
                    is SplashScreenEvent.Unauthenticated -> navigator.replaceAll(LoginScreen)
                }
            }
        }

        SpikeScreen {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                SpikeProgress()
            }
        }
    }
}