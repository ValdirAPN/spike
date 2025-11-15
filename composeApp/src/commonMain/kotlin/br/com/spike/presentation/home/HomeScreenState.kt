package br.com.spike.presentation.home

import br.com.spike.domain.model.Match
import br.com.spike.domain.model.User

data class HomeScreenState(
    val user: User = User(),
    val upcomingMatches: List<Match> = emptyList(),
)