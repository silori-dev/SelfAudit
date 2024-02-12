package dev.silori.selfaudit.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.silori.selfaudit.navigation.bottomBarNavigation.BottomBarGraph

@Composable
fun BaseScreen(
    navHostController: NavHostController = rememberNavController()
) {

    Scaffold(
        bottomBar = { BottomBar(navController = navHostController) },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            BottomBarGraph(navHostController = navHostController)
        }
    }

}