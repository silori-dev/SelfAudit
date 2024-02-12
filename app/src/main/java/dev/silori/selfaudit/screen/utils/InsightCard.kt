package dev.silori.selfaudit.screen.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.silori.selfaudit.data.entity.AuditData
import dev.silori.selfaudit.ui.theme.LightRed
import dev.silori.selfaudit.utillClass.dateFormatter

@Composable
fun InsightCard(
    auditData: AuditData
) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val widthInDp = configuration.screenWidthDp.dp

    val brush = Brush.horizontalGradient(
        colors = listOf(
            MaterialTheme.colors.primary,
            LightRed,
        ),
    )

    Card(
        modifier = Modifier.width(widthInDp.fraction(0.9f)),
        shape = RoundedCornerShape(16.dp),
        contentColor = Color.Black
    ) {
        Box(
            modifier = Modifier
                .background(brush)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .horizontalScroll(rememberScrollState())
            ) {
                CardSurfaceGood(
                    maxWidthSize = widthInDp,
                    firstWork = auditData.firstWork,
                    secondWork = auditData.secondWork, date = auditData.date.dateFormatter()
                )
                CardSurfaceBad(
                    maxWidthSize = widthInDp,
                    firstMess = auditData.firstMess,
                    secondMess = auditData.secondMess
                )
            }
            Text(
                modifier = Modifier
                    .padding(end = 32.dp, top = 4.dp)
                    .align(Alignment.TopEnd),
                text = "Productivity = ${auditData.productivity}",
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun CardSurfaceGood(
    date: String,
    maxWidthSize: Dp,
    firstWork: String,
    secondWork: String,
) {
    Row(
    ) {
        Surface(
            modifier = Modifier
                .width(maxWidthSize.fraction(0.85f))
                .fillMaxHeight(),
            color = MaterialTheme.colors.primary,
            shape = RoundedCornerShape(16.dp),
            contentColor = Color.Black
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                Text(text = date, fontWeight = FontWeight.Bold)
                Text(
                    text = firstWork
                )
                Text(
                    text = secondWork
                )
            }
        }
        Spacer(modifier = Modifier.width(maxWidthSize.fraction(0.05f)))
    }
}

@Composable
fun CardSurfaceBad(
    maxWidthSize: Dp,
    firstMess: String,
    secondMess: String,
) {
    Row(
    ) {
        Spacer(modifier = Modifier.width(maxWidthSize.fraction(0.05f)))
        Surface(
            modifier = Modifier
                .width(maxWidthSize.fraction(0.85f))
                .fillMaxHeight(),

            color = LightRed,
            shape = RoundedCornerShape(16.dp),
            contentColor = Color.Black
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            ) {
                Text(text = "")
                Text(
                    text = firstMess
                )
                Text(
                    text = secondMess
                )
            }
        }
    }
}


fun Dp.fraction(percent: Float) = if (percent > 1f) this else (this * percent)