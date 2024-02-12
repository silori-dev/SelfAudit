package dev.silori.selfaudit.navigation.bottomBarNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.silori.selfaudit.screen.audit.AuditScreen
import dev.silori.selfaudit.screen.insight.InsightScreen

@Composable
fun BottomBarGraph(
    navHostController: NavHostController,
    ) {
    NavHost(
        navController = navHostController,
        startDestination = BottomBarScreen.Audit.route
    ) {
        composable(BottomBarScreen.Audit.route) {
            AuditScreen()
        }
        composable( BottomBarScreen.Insight.route) {
            InsightScreen()
        }
    }
}