package br.com.spike.domain.repository

import br.com.spike.domain.model.User

interface ProfileRepository {

    suspend fun fetchUserData(): User
    fun signOut()
}