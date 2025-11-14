package br.com.spike.data.service

import br.com.spike.data.FirebaseAuthentication
import br.com.spike.data.FirebaseFirestore
import br.com.spike.data.model.UserResponse
import br.com.spike.domain.service.AuthService
import br.com.spike.domain.model.User

private const val USERS_COLLECTION = "users"

class AuthServiceImpl(
    private val firebaseAuthentication: FirebaseAuthentication,
    private val firebaseFirestore: FirebaseFirestore,
) : AuthService {
    override suspend fun currentUser(): User? {
        val currentUser = firebaseAuthentication.currentUser() ?: return null

        val userDocument = firebaseFirestore.read(
            collection = USERS_COLLECTION,
            document = currentUser.uid,
            kClass = UserResponse::class
        )?.data

        return User(
            id = currentUser.uid,
            name = userDocument?.name.orEmpty(),
            avatarUrl = userDocument?.avatarUrl.orEmpty(),
        )
    }

    override suspend fun signUp(email: String, password: String): String? {
        return firebaseAuthentication.signUp(email, password)
    }

    override suspend fun signIn(email: String, password: String): String? {
        return firebaseAuthentication.signIn(email, password)
    }

    override fun signOut() {
        firebaseAuthentication.signOut()
    }
}