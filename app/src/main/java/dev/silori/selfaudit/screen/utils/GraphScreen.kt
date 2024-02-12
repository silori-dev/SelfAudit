package dev.silori.selfaudit.screen.utils

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.axis.Axis
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun GraphScreen(
    graphData: ChartEntryModelProducer
) {

    val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMM")
    val horizontalAxisValueFormatter =
        AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _ ->
            (LocalDate.ofEpochDay(value.toLong())).format(dateTimeFormatter)
        }

    val lineChart = lineChart(
        axisValuesOverrider = AxisValuesOverrider.fixed(minY = 0f, maxY = 10f),
    )

    Chart(
        modifier = Modifier
            .fillMaxHeight(0.4f)
            .fillMaxWidth(),
        chart = lineChart,
        chartModelProducer = graphData,
        startAxis = rememberStartAxis(sizeConstraint = Axis.SizeConstraint.Auto(), itemPlacer = AxisItemPlacer.Vertical.default(maxItemCount = 11)),
        bottomAxis = rememberBottomAxis(valueFormatter = horizontalAxisValueFormatter, labelRotationDegrees = 300f),
    )

}