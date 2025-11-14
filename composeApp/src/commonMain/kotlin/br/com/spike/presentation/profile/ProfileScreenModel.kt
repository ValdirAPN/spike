package br.com.spike.presentation.profile

import br.com.spike.domain.service.AuthService
import cafe.adriel.voyager.core.model.ScreenModel

class ProfileScreenModel(
    private val authService: AuthService,
) : ScreenModel {

    fun signOut() {
        authService.signOut()
    }
}