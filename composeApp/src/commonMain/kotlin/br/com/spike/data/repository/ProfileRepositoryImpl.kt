package br.com.spike.data.repository

import br.com.spike.domain.model.User
import br.com.spike.domain.repository.AuthRepository
import br.com.spike.domain.repository.ProfileRepository

class ProfileRepositoryImpl(
    private val authRepository: AuthRepository
) : ProfileRepository {
    override suspend fun fetchUserData(): User {
        return authRepository.currentUser()
    }

    override fun signOut() {
        authRepository.signOut()
    }
}