package dev.silori.selfaudit.screen.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    showErrorFlow: StateFlow<Boolean>
) {

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

            ErrorTextField(
                workFlow = firstWorkFlow,
                updateWork = updateFirstWork,
                showErrorFlow = showErrorFlow
            )
            Spacer(modifier = Modifier.height(8.dp))
            ErrorTextField(
                workFlow = secondWorkFlow,
                updateWork = updateSecondWork,
                showErrorFlow = showErrorFlow
            )
        }
    }
}

// TODO : Add Keyboard Next Item

@Composable
fun ErrorTextField(
    workFlow: StateFlow<String>,
    updateWork: (String) -> Unit,
    showErrorFlow: StateFlow<Boolean>
) {

    val showError by showErrorFlow.collectAsState()
    val firstWork by workFlow.collectAsState()

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = firstWork,
        onValueChange = {
            updateWork(it)
        },
        isError = showError,
        trailingIcon = {
            if (showError) {
                Icon(imageVector = Icons.Default.Error, contentDescription = null)
            }
        },
        label = {
            if (showError)
                Text(text = "*Required")
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Black,
            errorCursorColor = MaterialTheme.colors.error,
            errorIndicatorColor = MaterialTheme.colors.error,
            errorLabelColor = MaterialTheme.colors.error,
            errorTrailingIconColor = MaterialTheme.colors.error,
            trailingIconColor = Color.Black,
            focusedLabelColor = MaterialTheme.colors.error,
            unfocusedLabelColor = MaterialTheme.colors.error,
            unfocusedIndicatorColor = Color.Black,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
    )
}