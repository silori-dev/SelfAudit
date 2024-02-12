package dev.silori.selfaudit.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import dev.silori.selfaudit.navigateToRoute
import dev.silori.selfaudit.navigation.Destination

@Composable
fun NameScreen(
    navController: NavHostController,
    saveDestination: (destination: Destination) -> Unit,
    ) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Name Screen")
            Button(onClick = {

                saveDestination(Destination.BaseScreen)
                navController.navigateToRoute(Destination.BaseScreen.route)

            }) {
                Text(text = "To Main Screen")
            }
        }
    }
}