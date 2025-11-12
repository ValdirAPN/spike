package br.com.spike.data

expect fun buildFirebaseAuthProvider(): FirebaseAuthProvider

interface FirebaseAuthProvider {
    fun currentUser(): FirebaseUser?
    suspend fun signUp(email: String, password: String): String?
    suspend fun signIn(email: String, password: String): String?
    fun signOut()
}