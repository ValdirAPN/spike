package br.com.spike.data.repository

import br.com.spike.data.FirebaseFirestore
import br.com.spike.data.FirestoreFilter
import br.com.spike.data.FirestoreFilterType
import br.com.spike.data.mapper.toDomain
import br.com.spike.data.model.MatchDto
import br.com.spike.data.model.PlayerDto
import br.com.spike.domain.model.Match
import br.com.spike.domain.repository.AuthRepository
import br.com.spike.domain.repository.MatchRepository
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.minus
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

private const val MATCH_COLLECTION = "matches"

@OptIn(ExperimentalTime::class)
class MatchRepositoryImpl(
    private val firebaseFirestore: FirebaseFirestore,
    private val authRepository: AuthRepository,
) : MatchRepository {
    override suspend fun create(match: MatchDto) {
        val organizer = authRepository.currentUser()
        val organizerAsPlayer = PlayerDto(
            uid = organizer.uid,
            username = organizer.username,
            avatarUrl = organizer.avatarUrl
        )
        firebaseFirestore.save(
            collection = MATCH_COLLECTION,
            data = match.copy(
                players = listOf(organizerAsPlayer),
                playerIds = listOf(organizer.uid),
                organizer = organizerAsPlayer,
            )
        )
    }

    override suspend fun update(id: String, match: MatchDto): Match? {
        firebaseFirestore.save(collection = MATCH_COLLECTION, document = id, match)
        return get(id)
    }

    override suspend fun getAll(): List<Match> {
        val today = Clock.System.now()
            .minus(1, DateTimeUnit.HOUR)
            .toEpochMilliseconds()
        val matches = firebaseFirestore.read(
            collection = MATCH_COLLECTION,
            filters = listOf(
                FirestoreFilter(
                    type = FirestoreFilterType.WhereGreaterThanOrEqualTo,
                    field = "startAtMillis",
                    value = today
                ),
            ),
            kClass = MatchDto::class
        )
        return matches.map { it.data.toDomain(it.id) }
    }

    override suspend fun get(id: String): Match? {
        val match = firebaseFirestore.read(
            MATCH_COLLECTION,
            document = id,
            kClass = MatchDto::class
        )
        return match?.run {
            this.data.toDomain(id = this.id)
        }
    }

    override suspend fun getUpcomingMatches(): List<Match> {
        val user = authRepository.currentUser()
        val today = Clock.System.now()
            .minus(1, DateTimeUnit.HOUR)
            .toEpochMilliseconds()
        val matches = firebaseFirestore
            .read(
                collection = MATCH_COLLECTION,
                filters = listOf(
                    FirestoreFilter(
                        type = FirestoreFilterType.WhereEqualTo,
                        field = "playerIds",
                        value = user.uid
                    ),
                    FirestoreFilter(
                        type = FirestoreFilterType.WhereEqualTo,
                        field = "owner",
                        value = user.uid
                    ),
                    FirestoreFilter(
                        type = FirestoreFilterType.WhereGreaterThanOrEqualTo,
                        field = "startAtMillis",
                        value = today
                    ),
                ),
                kClass = MatchDto::class
            )
        return matches.map { it.data.toDomain(it.id) }
    }
}