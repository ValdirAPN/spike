package br.com.spike.presentation.matchDetails

import br.com.spike.domain.model.Match
import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MatchDetailsScreenModel : ScreenModel {

    private val _state = MutableStateFlow(MatchDetailsState())
    val state = _state.asStateFlow()

    fun handleIntent(intent: MatchDetailsIntent) {
        when(intent) {
            is MatchDetailsIntent.AssembleData -> assembleData(intent.match)
        }
    }

    private fun assembleData(match: Match) {
        _state.update { oldState -> oldState.copy(match = match) }
    }
}