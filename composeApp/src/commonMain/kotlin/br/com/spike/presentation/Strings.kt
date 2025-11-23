package br.com.spike.presentation

import br.com.spike.domain.model.CourtType
import br.com.spike.domain.model.GenderPreference
import br.com.spike.domain.model.SkillLevel
import br.com.spike.domain.model.Visibility
import br.com.spike.presentation.matchForm.MatchFormPtStrings
import br.com.spike.presentation.matchForm.MatchFormStrings
import cafe.adriel.lyricist.LyricistStrings
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames

data class Strings(
    val courtType: (CourtType) -> String,
    val skillLevel: (SkillLevel) -> String,
    val genderPreference: (GenderPreference) -> String,
    val visibility: (Visibility) -> String,
    val daysOfWeekNames: DayOfWeekNames,
    val daysOfWeekNamesAbbrev: DayOfWeekNames,
    val monthNames: MonthNames,
    val monthNamesAbbrev: MonthNames,
    val matchFormStrings: MatchFormStrings,
)

@LyricistStrings(languageTag = "pt", default = true)
val PtStrings = Strings(
    courtType = { courtType ->
        when (courtType) {
            CourtType.BEACH -> "Praia"
            CourtType.INDOOR -> "Quadra"
        }
    },
    skillLevel = { skillLevel ->
        when (skillLevel) {
            SkillLevel.BEGINNER -> "Iniciante"
            SkillLevel.INTERMEDIARY -> "Intermediário"
            SkillLevel.ADVANCED -> "Avançado"
        }
    },
    genderPreference = { genderPreference ->
        when (genderPreference) {
            GenderPreference.MALE -> "Masculino"
            GenderPreference.FEMALE -> "Feminino"
            GenderPreference.MIXED -> "Misto"
        }
    },
    visibility = { visibility ->
        when (visibility) {
            Visibility.PUBLIC -> "Público"
            Visibility.PRIVATE -> "Privado"
        }
    },
    daysOfWeekNames = DayOfWeekNames(
        "Segunda",
        "Terça",
        "Quarta",
        "Quinta",
        "Sexta",
        "Sábado",
        "Domingo"
    ),
    daysOfWeekNamesAbbrev = DayOfWeekNames(
        "Seg",
        "Ter",
        "Qua",
        "Qui",
        "Sex",
        "Sáb",
        "Dom"
    ),
    monthNames = MonthNames(
        "Janeiro",
        "Fevereiro",
        "Março",
        "Abril",
        "Maio",
        "Junho",
        "Julho",
        "Agosto",
        "Setembro",
        "Outubro",
        "Novembro",
        "Dezembro"
    ),
    monthNamesAbbrev = MonthNames(
        "Jan",
        "Fev",
        "Mar",
        "Abr",
        "Mai",
        "Jun",
        "Jul",
        "Ago",
        "Set",
        "Out",
        "Nov",
        "Dez"
    ),
    matchFormStrings = MatchFormPtStrings
)