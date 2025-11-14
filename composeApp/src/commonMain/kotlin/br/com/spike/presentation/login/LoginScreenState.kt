package br.com.spike.presentation.login

import androidx.compose.foundation.text.input.TextFieldState
import br.com.spike.ui.components.SpikeButtonState

data class LoginScreenState(
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val buttonState: SpikeButtonState = SpikeButtonState.Default,
)
