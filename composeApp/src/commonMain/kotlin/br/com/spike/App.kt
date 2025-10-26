package br.com.spike

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import br.com.spike.home.HomeScreen
import cafe.adriel.voyager.navigator.Navigator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(HomeScreen)
    }
}