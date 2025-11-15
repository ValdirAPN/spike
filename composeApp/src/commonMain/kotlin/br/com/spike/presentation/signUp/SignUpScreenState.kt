package br.com.spike.presentation.signUp

import androidx.compose.foundation.text.input.TextFieldState
import br.com.spike.ui.components.SpikeButtonState

data class SignUpScreenState(
    val email: TextFieldState = TextFieldState(),
    val password: TextFieldState = TextFieldState(),
    val confirmPassword: TextFieldState = TextFieldState(),
    val name: TextFieldState = TextFieldState(),
    val username: TextFieldState = TextFieldState(),
    val avatarUrl: String = "",
    val buttonState: SpikeButtonState = SpikeButtonState.Default
)
