package br.com.spike.presentation.matchExplorer

import br.com.spike.domain.model.Match

data class MatchExplorerState(
    val matches: List<Match> = emptyList(),
    val isLoading: Boolean = false,
)
