package br.com.spike.data.repository

import br.com.spike.data.FirebaseFirestore
import br.com.spike.data.mapper.toDomain
import br.com.spike.data.model.MatchDto
import br.com.spike.data.model.PlayerDto
import br.com.spike.domain.model.Match
import br.com.spike.domain.repository.AuthRepository
import br.com.spike.domain.repository.MatchRepository

private const val MATCH_COLLECTION = "matches"

class MatchRepositoryImpl(
    private val firebaseFirestore: FirebaseFirestore,
    private val authRepository: AuthRepository,
) : MatchRepository {
    override suspend fun create(match: MatchDto) {
        val organizer = authRepository.currentUser()
        val organizerAsPlayer = PlayerDto(
            uid = organizer.id,
            username = organizer.username,
            avatarUrl = organizer.avatarUrl
        )
        firebaseFirestore.save(
            collection = MATCH_COLLECTION,
            data = match.copy(
                players = listOf(organizerAsPlayer),
                organizer = organizerAsPlayer,
            )
        )
    }

    override suspend fun getAll(): List<Match> {
        val matches = firebaseFirestore.read(MATCH_COLLECTION, kClass = MatchDto::class)
        return matches.map { it.data.toDomain(it.id) }
    }
}