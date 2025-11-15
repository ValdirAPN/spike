package br.com.spike.data.repository

import br.com.spike.domain.model.User
import br.com.spike.domain.repository.AuthRepository
import br.com.spike.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val authRepository: AuthRepository
) : HomeRepository {
    override suspend fun loadUserData(): User {
        return authRepository.currentUser()
    }
}