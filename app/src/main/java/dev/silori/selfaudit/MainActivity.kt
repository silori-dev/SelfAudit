package dev.silori.selfaudit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import dagger.hilt.android.AndroidEntryPoint
import dev.silori.selfaudit.ui.BaseScreen
import dev.silori.selfaudit.ui.theme.SelfAuditTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            SelfAuditTheme {

//                val viewModel: MainViewModel = hiltViewModel()
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//
//                    RootNavGraph(
//                        startDestination = viewModel.startDestinationRoute,
//                        changeStartDestination = { viewModel.saveStartDestination(it) }
//                    )
//                }
                BaseScreen()
            }
        }
    }
}

fun NavHostController.navigateToRoute(route: String) {
    this.navigate(route) {
        popUpTo(this@navigateToRoute.graph.findStartDestination().id)
        launchSingleTop = true
    }
}