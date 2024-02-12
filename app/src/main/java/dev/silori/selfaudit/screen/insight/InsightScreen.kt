package dev.silori.selfaudit.screen.insight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.silori.selfaudit.screen.utils.GraphScreen
import dev.silori.selfaudit.screen.utils.InsightCard
import dev.silori.selfaudit.screen.utils.TimeFrameRow

@Composable
fun InsightScreen(
    viewModel: InsightViewModel = hiltViewModel()
) {

    val listData by viewModel.insightData.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TimeFrameRow(
            selectedTimeFrame = viewModel.currentTimeFrame,
            onTimeFrameClicked = viewModel::changeTimeFrame
        )
        Spacer(modifier = Modifier.height(8.dp))
        GraphScreen(graphData = viewModel.chartEntryModelProducer)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 4.dp, end = 4.dp, bottom = 60.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            item {
                Text(fontSize = 24.sp, text = "Insights.......", fontWeight = FontWeight.Bold)
            }

            items(listData) { data ->
                InsightCard(auditData = data)
            }
        }
    }
}