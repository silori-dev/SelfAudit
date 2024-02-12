package dev.silori.selfaudit.screen.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WorkInputCard(
    title: String,
    backgroundColor: Color,
    firstWorkFlow: StateFlow<String>,
    updateFirstWork: (String) -> Unit,
    updateSecondWork: (String) -> Unit,
    secondWorkFlow: StateFlow<String>,
) {

    val firstWork = firstWorkFlow.collectAsState()
    val secondWork = secondWorkFlow.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = backgroundColor,
        contentColor = Color.Black
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = firstWork.value,
                onValueChange = {
                    updateFirstWork(it)
                })
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = secondWork.value,
                onValueChange = {
                    updateSecondWork(it)
                })
        }
    }
}