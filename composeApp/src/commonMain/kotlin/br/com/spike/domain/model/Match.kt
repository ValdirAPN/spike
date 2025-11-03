package br.com.spike.domain.model

data class Match(
    val id: String,
    val title: String,
    val spots: Int,
    val players: List<Player>,
    val organizer: Player,
)
