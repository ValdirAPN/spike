package br.com.spike.presentation.login

import br.com.spike.domain.repository.AuthRepository
import br.com.spike.ui.components.SpikeButtonState
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginScreenModel(
    private val authRepository: AuthRepository,
) : ScreenModel {
    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun authenticate(onSuccess: () -> Unit) = screenModelScope.launch {
        _state.update { oldState -> oldState.copy(buttonState = SpikeButtonState.Loading) }
        with(state.value) {
            val email = email.text.trim().toString()
            val password = password.text.trim().toString()
            val userId = authRepository.signIn(email, password)
            if (userId != null) {
                onSuccess()
            }
        }
        _state.update { oldState -> oldState.copy(buttonState = SpikeButtonState.Default) }
    }
}