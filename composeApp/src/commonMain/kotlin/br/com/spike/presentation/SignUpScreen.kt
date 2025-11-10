package br.com.spike.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.spike.ui.components.SpikeButton
import br.com.spike.ui.components.SpikeScreen
import br.com.spike.ui.components.SpikeTextField
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.ui.tooling.preview.Preview

object SignUpScreen : Screen {
    @Composable
    override fun Content() {
        SignUpScreenContent()
    }
}

@Composable
private fun SignUpScreenContent() {
    SpikeScreen {
        Column(modifier = Modifier.fillMaxSize()) {
            SpikeTextField(
                state = rememberTextFieldState(),
                label = "E-mail"
            )
            SpikeTextField(
                state = rememberTextFieldState(),
                label = "Senha"
            )
            SpikeTextField(
                state = rememberTextFieldState(),
                label = "Confirmar senha"
            )
            SpikeButton("Criar conta", action = {}, modifier = Modifier.padding(16.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun LoginScreenPreview() {
    SpikeTheme {
        SignUpScreenContent()
    }
}