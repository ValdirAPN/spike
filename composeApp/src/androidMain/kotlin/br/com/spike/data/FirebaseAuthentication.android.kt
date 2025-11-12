package br.com.spike.data

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.tasks.await

actual fun buildFirebaseAuthProvider(): FirebaseAuthProvider =
    AndroidFirebaseAuthProvider

private object AndroidFirebaseAuthProvider : FirebaseAuthProvider {
    override fun currentUser(): FirebaseUser? {
        val firebaseUser = Firebase.auth.currentUser ?: return null
        return FirebaseUser(
            uid = firebaseUser.uid,
            email = firebaseUser.email,
        )
    }

    override suspend fun signUp(email: String, password: String): String? {
        val authResult = Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        val firebaseUser = authResult.user ?: return null
        return firebaseUser.uid
    }

    override suspend fun signIn(email: String, password: String): String? {
        val authResult = Firebase.auth.signInWithEmailAndPassword(email, password).await()
        val firebaseUser = authResult.user ?: return null
        return firebaseUser.uid
    }

    override fun signOut() {
        Firebase.auth.signOut()
    }
}