package dev.silori.selfaudit.screen.audit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.silori.selfaudit.screen.utils.WorkInputCard
import dev.silori.selfaudit.ui.theme.LightRed
import kotlin.math.roundToInt

@Composable
fun AuditScreen(
    viewModel: AuditViewModel = hiltViewModel(),
) {
    val sliderPosition = viewModel.productivityStateFlow.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Text(text = "Auditing Screen", fontSize = 38.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            WorkInputCard(
                title = "What did you do today that moves you toward your goal?",
                backgroundColor = MaterialTheme.colors.primary,
                firstWorkFlow = viewModel.firstWorkStateFlow,
                updateFirstWork = viewModel::updateFirstWork,
                secondWorkFlow = viewModel.secondWorkStateFlow,
                updateSecondWork = viewModel::updateSecondWork,
            )

            WorkInputCard(
                title = "What did you do today that you shouldn't have done?",
                backgroundColor = LightRed,
                firstWorkFlow = viewModel.firstMessStateFlow,
                updateFirstWork = viewModel::updateFirstMess,
                secondWorkFlow = viewModel.secondMessStateFlow,
                updateSecondWork = viewModel::updateSecondMess,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Rate your productivity out of 10", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(text = sliderPosition.value.toInt().toString())

            Slider(
                value = sliderPosition.value,
                onValueChange = {
                    viewModel.updateProductivityPointer(value = it.roundToInt().toFloat())
                },
                steps = 9,
                valueRange = 0f..10f
            )

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
                Button(onClick = {
                    viewModel.saveTodayStats()
                },
                    shape = RoundedCornerShape(8.dp),
                    ) {
                    Text(text = "Submit", modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}