package br.com.spike.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.spike.presentation.signUp.SignUpScreen
import br.com.spike.presentation.home.HomeScreen
import br.com.spike.ui.components.SpikeButton
import br.com.spike.ui.components.SpikeButtonVariant
import br.com.spike.ui.components.SpikeScreen
import br.com.spike.ui.components.SpikeTextField
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kodein.di.compose.rememberInstance

object LoginScreen : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val screenModel by rememberInstance<LoginScreenModel>()
        val state by screenModel.state.collectAsStateWithLifecycle()

        LoginScreenContent(
            state = state,
            onClickLogin = {
                screenModel.authenticate {
                    navigator.replaceAll(HomeScreen)
                }
            },
            onClickCreateNewAccount = { navigator.replaceAll(SignUpScreen) }
        )
    }
}

@Composable
private fun LoginScreenContent(
    state: LoginScreenState,
    onClickLogin: () -> Unit,
    onClickCreateNewAccount: () -> Unit,
) = with (state) {
    SpikeScreen {
        Column(modifier = Modifier.fillMaxSize()) {
            SpikeTextField(
                state = email,
                label = "E-mail"
            )
            SpikeTextField(
                state = password,
                label = "Senha"
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                SpikeButton(
                    label = "Entrar",
                    action = onClickLogin,
                    state = buttonState
                )
                SpikeButton(
                    label = "Criar nova conta",
                    action = onClickCreateNewAccount,
                    variant = SpikeButtonVariant.NeutralSubtle
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun LoginScreenPreview() {
    SpikeTheme {
        LoginScreenContent(
            state = LoginScreenState(),
            onClickLogin = {},
            onClickCreateNewAccount = {},
        )
    }
}