package br.com.spike.presentation.profile

import br.com.spike.domain.repository.AuthRepository
import cafe.adriel.voyager.core.model.ScreenModel

class ProfileScreenModel(
    private val authRepository: AuthRepository,
) : ScreenModel {

    fun signOut() {
        authRepository.signOut()
    }
}