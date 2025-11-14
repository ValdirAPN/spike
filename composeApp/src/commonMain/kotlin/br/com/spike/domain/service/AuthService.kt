package br.com.spike.domain.service

import br.com.spike.domain.model.User

interface AuthService {
    suspend fun currentUser(): User?
    suspend fun signUp(email: String, password: String): String?
    suspend fun signIn(email: String, password: String): String?
    fun signOut()
}