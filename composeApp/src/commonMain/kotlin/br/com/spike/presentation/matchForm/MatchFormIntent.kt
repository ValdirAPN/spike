package br.com.spike.presentation.matchForm

import br.com.spike.domain.model.CourtType
import br.com.spike.domain.model.GenderPreference
import br.com.spike.domain.model.SkillLevel
import br.com.spike.domain.model.TeamSize
import br.com.spike.domain.model.Visibility

sealed interface MatchFormIntent {
    data class SetDate(val millis: Long?): MatchFormIntent
    data class SetStartTime(val hour: Int, val minute: Int): MatchFormIntent
    data class SetDuration(val hour: Int, val minute: Int): MatchFormIntent
    data class SetTeamSize(val teamSize: TeamSize): MatchFormIntent
    data class SetCourtType(val courtType: CourtType): MatchFormIntent
    data class SetSkillLevel(val skillLevel: SkillLevel): MatchFormIntent
    data class SetGenderPreference(val genderPreference: GenderPreference): MatchFormIntent
    data class SetVisibility(val visibility: Visibility): MatchFormIntent
    data object CreateMatch : MatchFormIntent
}