package br.com.spike.data.model

data class MatchDto(
    val title: String = "",
    val spots: Int = 0,
    val courtType: String = "",
    val skillLevel: String = "",
    val genderPreference: String = "",
    val visibility: String = "",
    val dateMillis: Long = 0,
    val startAtMillis: String = "",
    val durationMinutes: Int,
    val playersIds: List<String> = emptyList(),
    val organizerId: String,
)
