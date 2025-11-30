package br.com.spike.data.repository

import br.com.spike.data.FirebaseAuthentication
import br.com.spike.data.FirebaseFirestore
import br.com.spike.data.model.UserDto
import br.com.spike.domain.repository.AuthRepository
import br.com.spike.domain.model.User

private const val USERS_COLLECTION = "users"

class AuthRepositoryImpl(
    private val firebaseAuthentication: FirebaseAuthentication,
    private val firebaseFirestore: FirebaseFirestore,
) : AuthRepository {
    override suspend fun currentUser(): User {
        val currentUser = firebaseAuthentication.currentUser() ?: throw Exception("Unauthenticated")

        val userDocument = firebaseFirestore.read(
            collection = USERS_COLLECTION,
            document = currentUser.uid,
            kClass = UserDto::class
        )?.data

        return User(
            uid = currentUser.uid,
            name = userDocument?.name.orEmpty(),
            username = userDocument?.username.orEmpty(),
            avatarUrl = userDocument?.avatarUrl.orEmpty(),
        )
    }

    override suspend fun signUp(
        email: String,
        password: String,
        name: String,
        username: String,
    ): String {
        val userId = firebaseAuthentication.signUp(email, password) ?: throw Exception("Could not signup")
        val user = UserDto(
            name = name,
            username = username,
            avatarUrl = ""
        )

        firebaseFirestore.save(
            collection = USERS_COLLECTION,
            document = userId,
            data = user,
        )

        return userId
    }

    override suspend fun signIn(email: String, password: String): String? {
        return firebaseAuthentication.signIn(email, password)
    }

    override fun signOut() {
        firebaseAuthentication.signOut()
    }
}