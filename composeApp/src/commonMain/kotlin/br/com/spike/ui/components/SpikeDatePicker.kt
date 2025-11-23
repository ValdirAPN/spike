package br.com.spike.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.spike.ui.theme.SpikeTheme

@Composable
fun SpikeDatePicker(
    state: DatePickerState,
    modifier: Modifier = Modifier,
    showModeToggle: Boolean = false,
    title: String? = null,
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        title?.let {
            SpikeText(
                text = it,
                style = SpikeTheme.typography.labelSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .background(SpikeTheme.colors.backgroundBody)
        ) {
            DatePicker(
                state = state,
                modifier = Modifier.padding(bottom = 8.dp, top = 2.dp),
                colors = DatePickerDefaults.colors(
                    containerColor = SpikeTheme.colors.backgroundBody,
                    titleContentColor = SpikeTheme.colors.contentHigh,
                    headlineContentColor = SpikeTheme.colors.contentHigh,
                    weekdayContentColor = SpikeTheme.colors.contentLow,
                    subheadContentColor = SpikeTheme.colors.contentHigh,
                    navigationContentColor = SpikeTheme.colors.contentHigh,
                    yearContentColor = SpikeTheme.colors.contentHigh,
                    disabledYearContentColor = SpikeTheme.colors.contentLow,
                    currentYearContentColor = SpikeTheme.colors.contentHigh,
                    selectedYearContainerColor = SpikeTheme.colors.backgroundBrand,
                    selectedYearContentColor = SpikeTheme.colors.contentOnColorHigh,
                    dayContentColor = SpikeTheme.colors.contentHigh,
                    selectedDayContentColor = SpikeTheme.colors.contentOnColorHigh,
                    selectedDayContainerColor = SpikeTheme.colors.contentBrand,
                    disabledDayContentColor = SpikeTheme.colors.contentLow,
                    todayContentColor = SpikeTheme.colors.contentBrand,
                    todayDateBorderColor = SpikeTheme.colors.contentBrand,
                    dividerColor = Color.Transparent,
                ),
                showModeToggle = showModeToggle,
                title = null,
                headline = null,
            )
        }
    }
}