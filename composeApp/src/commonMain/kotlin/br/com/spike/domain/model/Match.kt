package br.com.spike.domain.model

data class Match(
    val id: String,
    val title: String,
    val spots: Int,
    val users: List<User>,
    val organizer: User,
)
