package br.com.spike.data.mapper

import br.com.spike.data.model.MatchDto
import br.com.spike.domain.model.CourtType
import br.com.spike.domain.model.GenderPreference
import br.com.spike.domain.model.Match
import br.com.spike.domain.model.SkillLevel
import br.com.spike.domain.model.TeamSize
import br.com.spike.domain.model.Visibility
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
fun MatchDto.toDomain(id: String): Match = Match(
    id = id,
    title = title,
    spots = spots,
    teamSize = TeamSize.fromString(teamSize),
    courtType = CourtType.fromString(courtType),
    skillLevel = SkillLevel.fromString(skillLevel),
    genderPreference = GenderPreference.fromString(genderPreference),
    visibility = Visibility.fromString(visibility),
    startAt = Instant.fromEpochMilliseconds(startAtMillis).toLocalDateTime(TimeZone.currentSystemDefault()),
    durationMinutes = durationMinutes,
    players = players.map { it.toDomain() },
    organizer = organizer?.toDomain()
)

@OptIn(ExperimentalTime::class)
fun Match.toData(): MatchDto = MatchDto(
    title = title,
    spots = spots,
    teamSize = teamSize.name,
    courtType = courtType.name,
    skillLevel = skillLevel.name,
    genderPreference = genderPreference.name,
    visibility = visibility.name,
    startAtMillis = startAt.toInstant(timeZone = TimeZone.currentSystemDefault()).toEpochMilliseconds(),
    durationMinutes = durationMinutes,
    players = players.map { it.toData() },
    organizer = organizer?.toData()
)