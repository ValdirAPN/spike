package br.com.spike.presentation.matchDetails

import br.com.spike.domain.model.Match

sealed interface MatchDetailsIntent {
    data class AssembleData(val match: Match): MatchDetailsIntent
    data object UpdateParticipantStatus: MatchDetailsIntent
}