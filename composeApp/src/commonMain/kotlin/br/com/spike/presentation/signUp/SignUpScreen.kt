package br.com.spike.presentation.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.spike.presentation.home.HomeScreen
import br.com.spike.presentation.login.LoginScreen
import br.com.spike.ui.components.SpikeButton
import br.com.spike.ui.components.SpikeButtonVariant
import br.com.spike.ui.components.SpikeScreen
import br.com.spike.ui.components.SpikeTextField
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.flow.collectLatest
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kodein.di.compose.rememberInstance

object SignUpScreen : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val screenModel by rememberInstance<SignUpScreenModel>()
        val state by screenModel.state.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            screenModel.events.collectLatest { event ->
                when (event) {
                    SignUpScreenEvent.Authenticated -> navigator.replaceAll(HomeScreen)
                }
            }
        }

        val createAccount = remember { { screenModel.signUp() } }

        SignUpScreenContent(
            state = state,
            onClickCreateAccount = createAccount,
            onClickNavigateToLogin = { navigator.replaceAll(LoginScreen) }
        )
    }
}

@Composable
private fun SignUpScreenContent(
    state: SignUpScreenState,
    onClickCreateAccount: () -> Unit,
    onClickNavigateToLogin: () -> Unit,
) = with(state) {
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
            SpikeTextField(
                state = confirmPassword,
                label = "Confirmar senha"
            )
            SpikeTextField(
                state = name,
                label = "Nome"
            )
            SpikeTextField(
                state = username,
                label = "Nome de usuário"
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                SpikeButton(
                    label = "Criar conta",
                    action = onClickCreateAccount,
                    state = buttonState,
                )
                SpikeButton(
                    label = "Já possui uma conta? Clique para acessar",
                    action = onClickNavigateToLogin,
                    variant = SpikeButtonVariant.NeutralSubtle
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SignUpScreenPreview() {
    SpikeTheme {
        SignUpScreenContent(
            state = SignUpScreenState(),
            onClickCreateAccount = {},
            onClickNavigateToLogin = {},
        )
    }
}