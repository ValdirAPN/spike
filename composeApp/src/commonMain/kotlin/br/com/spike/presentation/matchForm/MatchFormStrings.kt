package br.com.spike.presentation.matchForm

data class MatchFormStrings(
    val title: String,
    val titleLabel: String,
    val spotsLabel: String,
    val dateLabel: String,
    val startAtLabel: String,
    val durationLabel: String,
    val btnLabel: String,
)

val MatchFormPtStrings = MatchFormStrings(
    title = "Criar Partida",
    titleLabel = "Título",
    spotsLabel = "Vagas",
    dateLabel = "Selecionar data",
    startAtLabel = "Hora de Início",
    durationLabel = "Duração",
    btnLabel = "Criar",
)