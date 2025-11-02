package br.com.spike.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.spike.ui.theme.SpikeTheme

@Composable
fun SpikeHorizontalSelector(
    items: List<String>,
    onSelectItem: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    var selectedItem by remember { mutableStateOf(items.first()) }

    Box(modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = SpikeTheme.colors.backgroundBody,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(all = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items.forEach { item ->
                val background = if (item == selectedItem) {
                    SpikeTheme.colors.backgroundBrandVariant
                } else SpikeTheme.colors.backgroundBody

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(color = background)
                        .clickable {
                            selectedItem = item
                            onSelectItem(item)
                        }
                        .padding(
                            horizontal = 16.dp,
                            vertical = 12.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    SpikeText(item, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}