package br.com.spike.presentation.matchDetails

import br.com.spike.data.mapper.toData
import br.com.spike.data.mapper.toPlayer
import br.com.spike.domain.model.Match
import br.com.spike.domain.repository.AuthRepository
import br.com.spike.domain.repository.MatchRepository
import br.com.spike.ui.components.SpikeButtonState
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MatchDetailsScreenModel(
    private val matchRepository: MatchRepository,
    private val authRepository: AuthRepository
) : ScreenModel {

    private val _state = MutableStateFlow(MatchDetailsState())
    val state = _state.asStateFlow()

    fun handleIntent(intent: MatchDetailsIntent) {
        when (intent) {
            is MatchDetailsIntent.AssembleData -> assembleData(intent.match)
            is MatchDetailsIntent.UpdateParticipantStatus -> updateParticipantStatus()
        }
    }

    private fun assembleData(match: Match) {
        screenModelScope.launch {
            val user = authRepository.currentUser()
            val isParticipating = match.players.any { it.uid == user.uid }
            _state.update { oldState ->
                oldState.copy(
                    match = match,
                    isOwner = match.organizer?.uid == user.uid,
                    isParticipating = isParticipating,
                    btnLabel = if (isParticipating) "Não participar" else "Participar",
                    btnState = SpikeButtonState.Default
                )
            }
        }
    }

    private fun updateParticipantStatus() = with(_state.value) {
        screenModelScope.launch {
            _state.update { oldState -> oldState.copy(btnState = SpikeButtonState.Loading) }
            val user = authRepository.currentUser()
            val isParticipating = match?.players?.any {
                it.uid == user.uid
            } ?: throw Exception("ValueNotFound: Match")

            val newMatchPlayerList = if (isParticipating) {
                match.players.filter { it.uid != user.uid }
            } else match.players.plus(user.toPlayer())

            val newMatchData = match.copy(players = newMatchPlayerList)

            val updatedMatch = matchRepository.update(
                id = match.id,
                match = newMatchData.toData()
            ) ?: throw Exception("An error occurred while trying to update match ${match.id}")

            assembleData(updatedMatch)
        }
    }
}