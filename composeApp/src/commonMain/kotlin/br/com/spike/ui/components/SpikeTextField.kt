package br.com.spike.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import br.com.spike.ui.theme.SpikeTheme

@Composable
fun SpikeTextField(
    text: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    trailing: SpikeTextFieldTrailing? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    inputTransformation: InputTransformation? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    onClick: (() -> Unit)? = null,
) {
    SpikeTextField(
        state = TextFieldState(text),
        modifier = modifier,
        label = label,
        trailing = trailing,
        placeholder = placeholder,
        enabled = enabled,
        readOnly = readOnly,
        inputTransformation = inputTransformation,
        keyboardOptions = keyboardOptions,
        onKeyboardAction = onKeyboardAction,
        lineLimits = lineLimits,
        onClick = onClick
    )
}

@Composable
fun SpikeTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    label: String? = null,
    trailing: SpikeTextFieldTrailing? = null,
    placeholder: String? = null,
    enabled: Boolean = true,
    inputTransformation: InputTransformation? = null,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.Default,
    onClick: (() -> Unit)? = null,
) {

    val interactionSource = remember { MutableInteractionSource() }.also { source ->
        LaunchedEffect(source) {
            source.interactions.collect {
                if (it is PressInteraction.Release) {
                    onClick?.invoke()
                }
            }
        }
    }

    Column(
        modifier = modifier.padding(
            vertical = 8.dp,
            horizontal = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        label?.let {
            SpikeText(text = it, style = SpikeTheme.typography.labelSmall)
        }
        Row(
            modifier = Modifier
                .height(54.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(size = 16.dp))
                .background(
                    SpikeTheme.colors.backgroundBody,
                    shape = RoundedCornerShape(size = 16.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                BasicTextField(
                    interactionSource = interactionSource,
                    state = state,
                    modifier = Modifier
                        .fillMaxWidth(),
                    enabled = enabled,
                    readOnly = readOnly || onClick != null,
                    inputTransformation = inputTransformation,
                    textStyle = SpikeTheme.typography.bodyMedium.copy(color = SpikeTheme.colors.contentHigh),
                    keyboardOptions = keyboardOptions,
                    onKeyboardAction = onKeyboardAction,
                    lineLimits = lineLimits,
                    cursorBrush = SolidColor(SpikeTheme.colors.contentHigh)
                )
                if (placeholder.isNullOrEmpty().not() && state.text.isEmpty()) {
                    SpikeText(
                        text = placeholder,
                        style = SpikeTheme.typography.bodyMedium,
                        color = SpikeTheme.colors.contentLow
                    )
                }
            }
            when (trailing) {
                is SpikeTextFieldTrailing.Icon -> SpikeIcon(trailing.icon)
                is SpikeTextFieldTrailing.Button -> SpikeIconButton(
                    icon = trailing.icon,
                    action = trailing.action
                )

                null -> {}
            }
        }
    }
}

sealed interface SpikeTextFieldTrailing {
    data class Icon(
        val icon: SpikeIcons
    ) : SpikeTextFieldTrailing

    data class Button(
        val icon: SpikeIcons,
        val action: () -> Unit,
    ) : SpikeTextFieldTrailing
}