package br.com.spike.presentation.matchDetails

import br.com.spike.domain.model.Match
import br.com.spike.ui.components.SpikeButtonState

data class MatchDetailsState(
    val match: Match? = null,
    val btnState: SpikeButtonState = SpikeButtonState.Default,
    val btnLabel: String = "",
)
