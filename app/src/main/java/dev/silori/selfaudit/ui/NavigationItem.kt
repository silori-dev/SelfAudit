package dev.silori.selfaudit.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.silori.selfaudit.ui.theme.Green700

@Composable
fun RowScope.NavigationItem(
    label: @Composable () -> Unit,
    icon: @Composable () -> Unit,
    selected: Boolean,
    onClickEvent: () -> Unit,
) {
    val backgroundColor by animateColorAsState(targetValue = if (selected) Green700 else MaterialTheme.colors.surface)

    Box(
        modifier = Modifier.weight(1f),
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            shape = CircleShape,
            color = backgroundColor,
            contentColor = MaterialTheme.colors.onSurface
        ) {
            Column(
                modifier = Modifier
                    .selectable(
                        selected = selected, onClick = onClickEvent
                    )
                    .padding(horizontal = 32.dp, vertical = 4.dp)
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                icon()
                AnimatedVisibility(visible = selected) {
                    label()
                }
            }
        }
    }
}