package br.com.spike.presentation.splash

import br.com.spike.domain.model.User
import br.com.spike.domain.repository.AuthRepository
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

sealed interface SplashScreenEvent {
    data class Authenticated(val user: User) : SplashScreenEvent
    data object Unauthenticated : SplashScreenEvent
}

class SplashScreenModel(
    private val authRepository: AuthRepository,
) : ScreenModel {

    private val _events = MutableSharedFlow<SplashScreenEvent>(replay = 1)
    val events = _events.asSharedFlow()

    fun checkAuthState() = screenModelScope.launch {
        val user = try {
            authRepository.currentUser()
        } catch (e: Exception) {
            null
        }
        val event = if (user != null) {
            SplashScreenEvent.Authenticated(user)
        } else SplashScreenEvent.Unauthenticated
        _events.emit(event)
    }
}