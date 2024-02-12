package dev.silori.selfaudit.screen.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.silori.selfaudit.utillClass.LastMonth
import dev.silori.selfaudit.utillClass.SecondLastMonth
import dev.silori.selfaudit.utillClass.ThisMonth
import dev.silori.selfaudit.utillClass.ThisYear
import dev.silori.selfaudit.utillClass.TimeFrame
import kotlinx.coroutines.flow.StateFlow

val listOfTimeFrame = listOf(
    SecondLastMonth, LastMonth, ThisMonth, ThisYear
)

@Composable
fun TimeFrameRow(
    selectedTimeFrame: StateFlow<TimeFrame>,
    onTimeFrameClicked : (timeFrame : TimeFrame) -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOfTimeFrame.forEach { timeFrame ->
            TimeFrameCard(
                thisTimeFrame = timeFrame,
                selectedTimeFrameTag = selectedTimeFrame,
                onTimeFrameClicked = onTimeFrameClicked
            )
        }
    }

}