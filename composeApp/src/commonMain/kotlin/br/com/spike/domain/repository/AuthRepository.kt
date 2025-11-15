package br.com.spike.domain.repository

import br.com.spike.domain.model.User

interface AuthRepository {
    suspend fun currentUser(): User
    suspend fun signUp(
        email: String,
        password: String,
        name: String,
        username: String,
    ): String
    suspend fun signIn(email: String, password: String): String?
    fun signOut()
}