package br.com.spike.data

import br.com.spike.data.model.FirebaseUser

expect fun buildFirebaseAuthentication(): FirebaseAuthentication

interface FirebaseAuthentication {
    fun currentUser(): FirebaseUser?
    suspend fun signUp(email: String, password: String): String?
    suspend fun signIn(email: String, password: String): String?
    fun signOut()
}