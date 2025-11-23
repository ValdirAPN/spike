package br.com.spike.domain.model

import kotlinx.datetime.LocalDateTime
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
data class Match(
    val id: String,
    val title: String,
    val spots: Int,
    val courtType: CourtType,
    val skillLevel: SkillLevel,
    val genderPreference: GenderPreference,
    val visibility: Visibility,
    val startAt: LocalDateTime,
    val durationMinutes: Int,
    val players: List<Player>,
    val organizer: Player?,
)

enum class CourtType {
    BEACH, INDOOR;

    companion object {
        fun fromString(value: String): CourtType {
            return CourtType.entries.firstOrNull {
                it.name.equals(value, ignoreCase = true)
            } ?: BEACH
        }
    }
}

enum class SkillLevel {
    BEGINNER, INTERMEDIARY, ADVANCED;

    companion object {
        fun fromString(value: String): SkillLevel {
            return SkillLevel.entries.firstOrNull {
                it.name.equals(value, ignoreCase = true)
            } ?: BEGINNER
        }
    }
}

enum class GenderPreference {
    MIXED, MALE, FEMALE;

    companion object {
        fun fromString(value: String): GenderPreference {
            return GenderPreference.entries.firstOrNull {
                it.name.equals(value, ignoreCase = true)
            } ?: MIXED
        }
    }
}

enum class Visibility {
    PUBLIC, PRIVATE;

    companion object {
        fun fromString(value: String): Visibility {
            return Visibility.entries.firstOrNull {
                it.name.equals(value, ignoreCase = true)
            } ?: PUBLIC
        }
    }
}