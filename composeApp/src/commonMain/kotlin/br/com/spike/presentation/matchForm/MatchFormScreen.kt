package br.com.spike.presentation.matchForm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.then
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.spike.EventEffect
import br.com.spike.domain.model.CourtType
import br.com.spike.domain.model.GenderPreference
import br.com.spike.domain.model.SkillLevel
import br.com.spike.domain.model.TeamSize
import br.com.spike.domain.model.Visibility
import br.com.spike.domain.utils.isDigitsOnly
import br.com.spike.presentation.PtStrings
import br.com.spike.presentation.Strings
import br.com.spike.ui.components.SpikeButton
import br.com.spike.ui.components.SpikeDatePicker
import br.com.spike.ui.components.SpikeHorizontalSelector
import br.com.spike.ui.components.SpikeScreen
import br.com.spike.ui.components.SpikeTextField
import br.com.spike.ui.components.SpikeTimeSelect
import br.com.spike.ui.components.SpikeTopBar
import br.com.spike.ui.theme.SpikeTheme
import cafe.adriel.lyricist.LocalStrings
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.kodein.rememberScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

object MatchFormScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val strings = LocalStrings.current
        val screenModel = rememberScreenModel<MatchFormScreenModel>()
        val state by screenModel.state.collectAsStateWithLifecycle()

        val handleIntent: (intent: MatchFormIntent) -> Unit = remember {
            { intent ->
                screenModel.handleIntent(intent)
            }
        }

        EventEffect(
            eventEmitter = screenModel,
            onEvent = { event ->
                when (event) {
                    MatchFormScreenEvent.CreatedSuccessfully -> navigator.pop()
                }
            }
        )

        MatchFormContent(
            state = state,
            strings = strings,
            handleIntent = handleIntent,
            onNavigateBack = navigator::pop
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
private fun MatchFormContent(
    state: MatchFormScreenState,
    strings: Strings,
    handleIntent: (MatchFormIntent) -> Unit,
    onNavigateBack: () -> Unit
) = with(state) {
    var showStartTimeModal by remember { mutableStateOf(false) }
    var showDurationModal by remember { mutableStateOf(false) }

    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            val today = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return Instant
                    .fromEpochMilliseconds(utcTimeMillis)
                    .toLocalDateTime(TimeZone.UTC).date >= today
            }

            override fun isSelectableYear(year: Int): Boolean {
                return year >= today.year
            }
        }
    )

    LaunchedEffect(datePickerState.selectedDateMillis) {
        handleIntent(MatchFormIntent.SetDate(millis = datePickerState.selectedDateMillis))
    }

    SpikeScreen(
        topBar = {
            SpikeTopBar(
                title = strings.matchFormStrings.title,
                onNavigateBack = onNavigateBack
            )
        }
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            SpikeTextField(
                state = title.content,
                label = strings.matchFormStrings.titleLabel,
                error = title.error,
            )
            SpikeTextField(
                state = spots.content,
                label = strings.matchFormStrings.spotsLabel,
                inputTransformation = InputTransformation.maxLength(2).then {
                    if (!asCharSequence().toString().isDigitsOnly() || asCharSequence().startsWith('0')) {
                        revertAllChanges()
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                error = spots.error,
            )
            SpikeDatePicker(
                state = datePickerState,
                title = strings.matchFormStrings.dateLabel,
                error = dateMillis.error
            )
            SpikeTextField(
                text = startAt.content?.toString().orEmpty(),
                label = strings.matchFormStrings.startAtLabel,
                onClick = { showStartTimeModal = true },
                error = startAt.error,
            )
            SpikeTextField(
                text = duration.content?.toString().orEmpty(),
                label = strings.matchFormStrings.durationLabel,
                onClick = { showDurationModal = true },
                error = duration.error,
            )
            SpikeHorizontalSelector(
                items = TeamSize.entries,
                itemToString = { item ->
                    strings.teamSize(item)
                },
                onSelectItem = { teamSize ->
                    handleIntent(MatchFormIntent.SetTeamSize(teamSize))
                }
            )
            SpikeHorizontalSelector(
                items = CourtType.entries,
                itemToString = { item ->
                    strings.courtType(item)
                },
                onSelectItem = { courtType ->
                    handleIntent(MatchFormIntent.SetCourtType(courtType))
                }
            )
            SpikeHorizontalSelector(
                items = SkillLevel.entries,
                itemToString = { item ->
                    strings.skillLevel(item)
                },
                onSelectItem = { skillLevel ->
                    handleIntent(MatchFormIntent.SetSkillLevel(skillLevel))
                }
            )
            SpikeHorizontalSelector(
                items = GenderPreference.entries,
                itemToString = { item ->
                    strings.genderPreference(item)
                },
                onSelectItem = { genderPreference ->
                    handleIntent(MatchFormIntent.SetGenderPreference(genderPreference))
                }
            )
            SpikeHorizontalSelector(
                items = Visibility.entries,
                itemToString = { item ->
                    strings.visibility(item)
                },
                onSelectItem = { visibility ->
                    handleIntent(MatchFormIntent.SetVisibility(visibility))
                }
            )
            Spacer(Modifier.weight(1f))
            SpikeButton(
                label = strings.matchFormStrings.btnLabel,
                state = buttonState,
                action = {
                    handleIntent(MatchFormIntent.CreateMatch)
                },
                modifier = Modifier.padding(16.dp)
            )

            if (showStartTimeModal) {
                SpikeTimeSelect(
                    onChange = { hour, minute ->
                        handleIntent(MatchFormIntent.SetStartTime(hour, minute))
                        showStartTimeModal = false
                    },
                    onDismissRequest = { showStartTimeModal = false },
                    selectedTime = state.startAt.content,
                )
            }

            if (showDurationModal) {
                SpikeTimeSelect(
                    onChange = { hour, minute ->
                        handleIntent(MatchFormIntent.SetDuration(hour, minute))
                        showDurationModal = false
                    },
                    onDismissRequest = { showDurationModal = false },
                    selectedTime = state.duration.content,
                )
            }
        }
    }
}

@Composable
@Preview
private fun MatchFormContentPreview() {
    SpikeTheme {
        MatchFormContent(
            state = MatchFormScreenState(),
            strings = PtStrings,
            handleIntent = {},
            onNavigateBack = {},
        )
    }
}