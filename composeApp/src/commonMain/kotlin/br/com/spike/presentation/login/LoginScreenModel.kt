package br.com.spike.presentation.login

import br.com.spike.domain.service.AuthService
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginScreenModel(
    private val authService: AuthService,
) : ScreenModel {
    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    fun authenticate(onSuccess: () -> Unit) = screenModelScope.launch {
        with(state.value) {
            val email = email.text.trim().toString()
            val password = password.text.trim().toString()
            val user = authService.signIn(email, password)
            if (user != null) {
                onSuccess()
            }
        }
    }
}