package br.com.spike.presentation.matchForm

import androidx.compose.foundation.text.input.TextFieldState
import br.com.spike.domain.model.CourtType
import br.com.spike.domain.model.GenderPreference
import br.com.spike.domain.model.SkillLevel
import br.com.spike.domain.model.Visibility
import br.com.spike.ui.components.SpikeButtonState
import kotlinx.datetime.LocalTime

data class MatchFormScreenState(
    val title: MatchFormField<TextFieldState> = MatchFormField(content = TextFieldState()),
    val spots: MatchFormField<TextFieldState> = MatchFormField(content = TextFieldState()),
    val dateMillis: MatchFormField<Long?> = MatchFormField(content = null),
    val startAt: MatchFormField<LocalTime?> = MatchFormField(content = null),
    val duration: MatchFormField<LocalTime?> = MatchFormField(content = null),
    val courtType: CourtType = CourtType.BEACH,
    val skillLevel: SkillLevel = SkillLevel.BEGINNER,
    val genderPreference: GenderPreference = GenderPreference.MIXED,
    val visibility: Visibility = Visibility.PUBLIC,
    val buttonState: SpikeButtonState = SpikeButtonState.Default,
)

data class MatchFormField<T>(
    val content: T,
    val error: String? = null,
)