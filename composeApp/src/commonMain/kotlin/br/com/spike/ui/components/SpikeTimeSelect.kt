package br.com.spike.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import br.com.spike.ui.theme.SpikeTheme
import kotlinx.datetime.LocalTime
import kotlin.math.abs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpikeTimeSelect(
    onChange: (hour: Int, minute: Int) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    selectedTime: LocalTime? = null,
) {
    var selectedHour by remember { mutableStateOf(selectedTime?.hour ?: 0) }
    var selectedMinute by remember { mutableStateOf(selectedTime?.minute ?: 0) }

    SpikeModalBottomSheet(
        onDismissRequest = onDismissRequest,
        content = {
            Column(
                modifier = modifier.padding(horizontal = 16.dp).padding(bottom = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val hours = remember { (0..23).toList() }
                    val minutes = remember { (0..59).toList() }

                    TimeColumn(
                        items = hours,
                        selectedItem = selectedHour,
                        onChangeItem = { selectedHour = it }
                    )
                    TimeColumn(
                        items = minutes,
                        selectedItem = selectedMinute,
                        onChangeItem = { selectedMinute = it }
                    )
                }
                SpikeButton(
                    label = "Confirmar",
                    action = {
                        onChange(
                            selectedHour,
                            selectedMinute,
                        )
                    }
                )
            }
        }
    )
}

@Composable
private fun RowScope.TimeColumn(
    items: List<Int>,
    selectedItem: Int,
    onChangeItem: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyColumnState = rememberLazyListState(
        initialFirstVisibleItemIndex = (items.size * 1_000 + selectedItem) - 2
    )
    val snappingLayout = remember(lazyColumnState) { SnapLayoutInfoProvider(lazyColumnState) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    val itemHeight = 48
    val totalHeight = itemHeight * 5

    val density = LocalDensity.current
    val centerPx = with(density) { (totalHeight.dp / 2).toPx() }

    LaunchedEffect(lazyColumnState) {
        snapshotFlow { lazyColumnState.firstVisibleItemIndex }
            .collect { inProgress ->
                val selectedItem = (lazyColumnState.firstVisibleItemIndex + 2) % items.size
                onChangeItem(selectedItem)
            }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.weight(1f)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(itemHeight.dp)
                .background(
                    color = SpikeTheme.colors.backgroundBrand,
                    shape = RoundedCornerShape(16.dp)
                )
        )
        LazyColumn(
            state = lazyColumnState,
            modifier = Modifier.height(totalHeight.dp),
            flingBehavior = flingBehavior
        ) {
            items(count = Int.MAX_VALUE) { index ->
                val layoutInfo = lazyColumnState.layoutInfo
                val itemInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }

                // Compute smooth distance factor: 0f in center → 1f far away
                val distanceFraction = itemInfo?.let {
                    val itemCenter = it.offset + it.size / 2f
                    val dist = abs(itemCenter - centerPx)
                    (dist / (centerPx)).coerceIn(0f, 1f)
                } ?: 1f

                // Animate a weight from 0 to 1 where 0 = selected, 1 = unselected
                val animatedFraction = animateFloatAsState(
                    targetValue = distanceFraction,
                    animationSpec = tween(durationMillis = 150)
                ).value

                // Smoothly interpolate colors
                val color = lerp(
                    SpikeTheme.colors.contentOnColorHigh,   // selected
                    SpikeTheme.colors.contentHigh,          // unselected
                    animatedFraction
                )

                Box(
                    modifier = Modifier.fillMaxWidth().height(itemHeight.dp),
                    contentAlignment = Alignment.Center
                ) {
                    SpikeText(
                        text = (index % items.size).toString().padStart(2, '0'),
                        modifier = Modifier,
                        color = color
                    )
                }
            }
        }
    }
}