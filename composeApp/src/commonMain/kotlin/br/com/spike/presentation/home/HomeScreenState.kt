package br.com.spike.presentation.home

import br.com.spike.domain.model.Match

data class HomeScreenState(
    val username: String = "",
    val avatarUrl: String = "",
    val upcomingMatches: List<Match> = emptyList(),
)