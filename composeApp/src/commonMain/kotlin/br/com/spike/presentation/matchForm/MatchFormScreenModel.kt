package br.com.spike.presentation.matchForm

import br.com.spike.EventEmitter
import br.com.spike.EventEmitterHandler
import br.com.spike.data.model.MatchDto
import br.com.spike.domain.mapper.toMinutes
import br.com.spike.domain.model.CourtType
import br.com.spike.domain.model.GenderPreference
import br.com.spike.domain.model.SkillLevel
import br.com.spike.domain.model.TeamSize
import br.com.spike.domain.model.Visibility
import br.com.spike.domain.repository.MatchRepository
import br.com.spike.ui.components.SpikeButtonState
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

sealed interface MatchFormScreenEvent {
    data object CreatedSuccessfully : MatchFormScreenEvent
}

class MatchFormScreenModel(
    private val matchRepository: MatchRepository,
) : ScreenModel, EventEmitter<MatchFormScreenEvent> by EventEmitterHandler() {

    private val _state = MutableStateFlow(MatchFormScreenState())
    val state = _state.asStateFlow()

    fun handleIntent(intent: MatchFormIntent) {
        when (intent) {
            is MatchFormIntent.SetDate -> setDate(intent.millis)
            is MatchFormIntent.SetTeamSize -> setTeamSize(intent.teamSize)
            is MatchFormIntent.SetCourtType -> setCourtType(intent.courtType)
            is MatchFormIntent.SetDuration -> setDuration(intent.hour, intent.minute)
            is MatchFormIntent.SetGenderPreference -> setGenderPreference(intent.genderPreference)
            is MatchFormIntent.SetSkillLevel -> setSkillLevel(intent.skillLevel)
            is MatchFormIntent.SetStartTime -> setStartAt(intent.hour, intent.minute)
            is MatchFormIntent.SetVisibility -> setVisibility(intent.visibility)
            is MatchFormIntent.CreateMatch -> createMatch()
        }
    }

    private fun setDate(millis: Long?) {
        _state.update { oldState ->
            oldState.copy(
                dateMillis = MatchFormField(
                    content = millis,
                    error = null,
                )
            )
        }
    }

    private fun setStartAt(hour: Int, minute: Int) {
        _state.update { oldState ->
            oldState.copy(
                startAt = MatchFormField(
                    content = LocalTime(
                        hour = hour,
                        minute = minute,
                    ),
                    error = null,
                )
            )
        }
    }

    private fun setDuration(hour: Int, minute: Int) {
        _state.update { oldState ->
            oldState.copy(
                duration = MatchFormField(
                    content = LocalTime(
                        hour = hour,
                        minute = minute,
                    ),
                    error = null,
                )
            )
        }
    }

    private fun setTeamSize(teamSize: TeamSize) {
        _state.update { oldState ->
            oldState.copy(teamSize = teamSize)
        }
    }

    private fun setCourtType(courtType: CourtType) {
        _state.update { oldState ->
            oldState.copy(courtType = courtType)
        }
    }

    private fun setSkillLevel(skillLevel: SkillLevel) {
        _state.update { oldState ->
            oldState.copy(skillLevel = skillLevel)
        }
    }

    private fun setGenderPreference(genderPreference: GenderPreference) {
        _state.update { oldState ->
            oldState.copy(genderPreference = genderPreference)
        }
    }

    private fun setVisibility(visibility: Visibility) {
        _state.update { oldState ->
            oldState.copy(visibility = visibility)
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun createMatch() {
        screenModelScope.launch {
            if (!validateFields()) return@launch

            _state.update { oldState -> oldState.copy(buttonState = SpikeButtonState.Loading) }

            with(_state.value) {
                val startAtMillis = Instant
                    .fromEpochMilliseconds(dateMillis.content ?: 0)
                    .toLocalDateTime(TimeZone.UTC)
                    .run {
                        LocalDateTime(
                            year = this.year,
                            month = this.month,
                            day = this.day,
                            hour = startAt.content?.hour ?: 0,
                            minute = startAt.content?.minute ?: 0
                        )
                            .toInstant(TimeZone.currentSystemDefault())
                            .toEpochMilliseconds()
                    }

                val matchDto = MatchDto(
                    title = title.content.text.toString(),
                    spots = spots.content.text.toString().toInt(),
                    courtType = courtType.name,
                    skillLevel = skillLevel.name,
                    genderPreference = genderPreference.name,
                    visibility = visibility.name,
                    startAtMillis = startAtMillis,
                    durationMinutes = duration.content?.toMinutes() ?: 0,
                    players = emptyList(),
                    organizer = null,
                )

                println("VPNT - $matchDto")
                matchRepository.create(matchDto)
            }

            emitEvent(MatchFormScreenEvent.CreatedSuccessfully)
            _state.update { oldState -> oldState.copy(buttonState = SpikeButtonState.Default) }
        }
    }

    private fun validateFields(): Boolean = with(_state.value) {
        if (this.title.content.text.trim().length < TITLE_MIN_LENGTH) {
            _state.update { oldState ->
                oldState.copy(
                    title = this.title.copy(
                        error = "O título deve conter ao menos 4 caracteres"
                    )
                )
            }
            return false
        } else {
            _state.update { oldState ->
                oldState.copy(
                    title = this.title.copy(
                        error = null
                    )
                )
            }
        }

        val spots = this.spots.content.text.trim().toString().toIntOrNull()
        if (spots == null || spots < 4) {
            _state.update { oldState ->
                oldState.copy(
                    spots = this.spots.copy(
                        error = "A partida deve ter ao menos 4 vagas"
                    )
                )
            }
            return false
        } else {
            _state.update { oldState ->
                oldState.copy(
                    spots = this.spots.copy(
                        error = null
                    )
                )
            }
        }

        if (this.dateMillis.content == null) {
            _state.update { oldState ->
                oldState.copy(
                    dateMillis = this.dateMillis.copy(
                        error = "Informe a data da partida"
                    )
                )
            }
            return false
        } else {
            _state.update { oldState ->
                oldState.copy(
                    dateMillis = this.dateMillis.copy(
                        error = null
                    )
                )
            }
        }

        if (this.startAt.content == null) {
            _state.update { oldState ->
                oldState.copy(
                    startAt = this.startAt.copy(
                        error = "Informe a hora de início da partida"
                    )
                )
            }
            return false
        } else {
            _state.update { oldState ->
                oldState.copy(
                    startAt = this.startAt.copy(
                        error = null
                    )
                )
            }
        }

        if (this.duration.content == null || this.duration.content.toMinutes() < 30) {
            _state.update { oldState ->
                oldState.copy(
                    duration = this.duration.copy(
                        error = "A partida deve ter ao menos 30 minutos"
                    )
                )
            }
            return false
        } else {
            _state.update { oldState ->
                oldState.copy(
                    duration = this.duration.copy(
                        error = null
                    )
                )
            }
        }

        return true
    }
}

private const val TITLE_MIN_LENGTH = 4