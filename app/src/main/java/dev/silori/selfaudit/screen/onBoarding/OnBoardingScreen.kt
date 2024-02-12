package dev.silori.selfaudit.screen.onBoarding

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
fun OnBoardingScreen(
    navController: NavHostController,
    saveDestination: (destination: Destination) -> Unit,
    ) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column {
            Text(text = "OnBoardingScreen")
            Button(onClick = {
                saveDestination(Destination.NameScreen)
                navController.navigateToRoute(Destination.NameScreen.route)
            }) {
                Text(text = "To Name Screen")
            }
        }
    }
}