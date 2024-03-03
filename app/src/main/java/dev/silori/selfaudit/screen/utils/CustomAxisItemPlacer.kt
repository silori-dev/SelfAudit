package dev.silori.selfaudit.screen.utils

import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.vertical.VerticalAxis
import com.patrykandpatrick.vico.core.chart.draw.ChartDrawContext
import com.patrykandpatrick.vico.core.context.MeasureContext

class CustomAxisItemPlacer : AxisItemPlacer.Vertical {
    override fun getBottomVerticalAxisInset(
        verticalLabelPosition: VerticalAxis.VerticalLabelPosition,
        maxLabelHeight: Float,
        maxLineThickness: Float
    ): Float {
        return AxisItemPlacer.Vertical.default().getBottomVerticalAxisInset(
            verticalLabelPosition = verticalLabelPosition,
            maxLabelHeight = maxLabelHeight,
            maxLineThickness = maxLineThickness,
        )
    }

    override fun getHeightMeasurementLabelValues(
        context: MeasureContext,
        position: AxisPosition.Vertical
    ): List<Float> {
        return AxisItemPlacer.Vertical.default().getHeightMeasurementLabelValues(
            context = context,
            position = position
        )
    }

    override fun getLabelValues(
        context: ChartDrawContext,
        axisHeight: Float,
        maxLabelHeight: Float,
        position: AxisPosition.Vertical
    ): List<Float> {
        return listOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f)
    }

    override fun getTopVerticalAxisInset(
        verticalLabelPosition: VerticalAxis.VerticalLabelPosition,
        maxLabelHeight: Float,
        maxLineThickness: Float
    ): Float {
        return AxisItemPlacer.Vertical.default().getTopVerticalAxisInset(
            verticalLabelPosition = verticalLabelPosition,
            maxLabelHeight = maxLabelHeight,
            maxLineThickness = maxLineThickness
        )
    }

    override fun getWidthMeasurementLabelValues(
        context: MeasureContext,
        axisHeight: Float,
        maxLabelHeight: Float,
        position: AxisPosition.Vertical
    ): List<Float> {
        return AxisItemPlacer.Vertical.default().getWidthMeasurementLabelValues(
            context = context,
            axisHeight = axisHeight,
            maxLabelHeight = maxLabelHeight,
            position = position
        )
    }
}