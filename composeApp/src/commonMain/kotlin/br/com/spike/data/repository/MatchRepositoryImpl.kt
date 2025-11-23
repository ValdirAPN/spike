package br.com.spike.data.repository

import br.com.spike.data.FirebaseFirestore
import br.com.spike.data.model.MatchDto
import br.com.spike.domain.repository.AuthRepository
import br.com.spike.domain.repository.MatchRepository

private const val MATCH_COLLECTION = "matchs"

class MatchRepositoryImpl(
    private val firebaseFirestore: FirebaseFirestore,
    private val authRepository: AuthRepository,
) : MatchRepository {
    override suspend fun create(match: MatchDto) {
        val organizerId = authRepository.currentUser().id
        firebaseFirestore.save(
            collection = MATCH_COLLECTION,
            data = match.copy(
                playersIds = listOf(organizerId),
                organizerId = organizerId,
            )
        )
    }
}