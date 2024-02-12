package dev.silori.selfaudit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.silori.selfaudit.screen.onBoarding.OnBoardingScreen
import dev.silori.selfaudit.ui.BaseScreen
import dev.silori.selfaudit.ui.NameScreen

@Composable
fun RootNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: State<String>,
    changeStartDestination: (destination: Destination) -> Unit,
) {

    NavHost(
        navController = navController,
        startDestination = startDestination.value,
    ) {

        composable(route = Destination.OnBoardingScreen.route) {
            OnBoardingScreen(navController = navController, changeStartDestination)
        }
        composable(route = Destination.NameScreen.route) {
            NameScreen(navController = navController, changeStartDestination)
        }
        composable(route = Destination.BaseScreen.route) {
            BaseScreen()
        }

    }

}


//object RootGraph {
//    const val ON_BOARDING = "on_boarding"
//    const val NAME = "name"
//    const val BASE_SCREEN = "base_screen"
//}


enum class Destination(val route: String) {

    OnBoardingScreen(route = "on_boarding_screen"),
    NameScreen(route = "name_screen"),
    BaseScreen(route = "base_screen")

}