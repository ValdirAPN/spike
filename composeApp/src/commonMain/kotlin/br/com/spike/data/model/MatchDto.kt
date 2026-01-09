package br.com.spike.data.model

data class MatchDto(
    val title: String = "",
    val spots: Int = 0,
    val teamSize: String = "",
    val courtType: String = "",
    val skillLevel: String = "",
    val genderPreference: String = "",
    val visibility: String = "",
    val startAtMillis: Long = 0,
    val durationMinutes: Int = 0,
    val players: List<PlayerDto> = emptyList(),
    val playerIds: List<String> = emptyList(),
    val organizer: PlayerDto? = null,
)
