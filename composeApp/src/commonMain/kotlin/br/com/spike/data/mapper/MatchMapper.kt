package br.com.spike.data.mapper

import br.com.spike.data.model.MatchDto
import br.com.spike.domain.model.CourtType
import br.com.spike.domain.model.GenderPreference
import br.com.spike.domain.model.Match
import br.com.spike.domain.model.SkillLevel
import br.com.spike.domain.model.Visibility
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun MatchDto.toDomain(id: String): Match = Match(
    id = id,
    title = title,
    spots = spots,
    courtType = CourtType.fromString(courtType),
    skillLevel = SkillLevel.fromString(skillLevel),
    genderPreference = GenderPreference.fromString(genderPreference),
    visibility = Visibility.fromString(visibility),
    startAt = Instant.fromEpochMilliseconds(startAtMillis).toLocalDateTime(TimeZone.currentSystemDefault()),
    durationMinutes = durationMinutes,
    players = players.map { it.toDomain() },
    organizer = organizer?.toDomain()
)