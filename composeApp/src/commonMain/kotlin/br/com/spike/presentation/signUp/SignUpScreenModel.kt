package br.com.spike.presentation.signUp

import br.com.spike.domain.service.AuthService
import br.com.spike.ui.components.SpikeButtonState
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface SignUpScreenEvent {
    object Authenticated : SignUpScreenEvent
}

class SignUpScreenModel(
    private val authService: AuthService,
) : ScreenModel {

    private val _events = MutableSharedFlow<SignUpScreenEvent>(replay = 1)
    val events = _events.asSharedFlow()
    private val _state = MutableStateFlow(SignUpScreenState())
    val state = _state.asStateFlow()

    fun signUp() {
        screenModelScope.launch {
            _state.update { oldState -> oldState.copy(buttonState = SpikeButtonState.Loading) }
            with(_state.value) {
                val email = email.text.trim().toString()
                val password = password.text.trim().toString()
                val name = name.text.trim().toString()
                val username = username.text.trim().toString()

                authService.signUp(
                    email, password, name, username
                )

                _events.emit(SignUpScreenEvent.Authenticated)
            }
            _state.update { oldState -> oldState.copy(buttonState = SpikeButtonState.Default) }
        }
    }
}