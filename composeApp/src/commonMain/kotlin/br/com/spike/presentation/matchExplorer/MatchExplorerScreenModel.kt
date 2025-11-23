package br.com.spike.presentation.matchExplorer

import br.com.spike.domain.repository.MatchRepository
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MatchExplorerScreenModel(
    private val matchRepository: MatchRepository,
) : ScreenModel {

    private val _state = MutableStateFlow(MatchExplorerState())
    val state = _state.asStateFlow()

    fun loadMatches() {
        screenModelScope.launch {
            _state.update { oldState -> oldState.copy(isLoading = true) }
            val matches = matchRepository.getAll()
            _state.update { oldState -> oldState.copy(matches = matches, isLoading = false) }
        }
    }
}