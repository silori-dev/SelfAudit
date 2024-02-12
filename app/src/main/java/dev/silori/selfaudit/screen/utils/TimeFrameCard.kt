package dev.silori.selfaudit.screen.utils

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.silori.selfaudit.utillClass.TimeFrame
import kotlinx.coroutines.flow.StateFlow

@Composable
fun TimeFrameCard(
    thisTimeFrame: TimeFrame,
    selectedTimeFrameTag: StateFlow<TimeFrame>,
    onTimeFrameClicked: (timeFrame: TimeFrame) -> Unit,
    ) {

    val selectedTimeFrame by selectedTimeFrameTag.collectAsState()

    val thisColor by animateColorAsState(
        targetValue = if (thisTimeFrame == selectedTimeFrame) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.surface,
        label = ""
    )
    Card(
        shape = RoundedCornerShape(16.dp),
    ) {
        Surface(
            modifier = Modifier.clickable {
                onTimeFrameClicked(thisTimeFrame)
            },
            color = thisColor
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                text = thisTimeFrame.title
            )
        }
    }
}