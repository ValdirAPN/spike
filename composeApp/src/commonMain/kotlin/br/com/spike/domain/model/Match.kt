package br.com.spike.domain.model

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
data class Match(
    val id: String,
    val title: String,
    val spots: Int,
    val courtType: CourtType,
    val skillLevel: SkillLevel,
    val genderPreference: GenderPreference,
    val visibility: Visibility,
    val startAt: Instant,
    val durationMinutes: Int,
    val players: List<Player>,
    val organizer: User,
)

enum class CourtType {
    BEACH, INDOOR
}

enum class SkillLevel {
    BEGINNER, INTERMEDIARY, ADVANCED
}

enum class GenderPreference {
    MIXED, MALE, FEMALE
}

enum class Visibility {
    PUBLIC, PRIVATE
}