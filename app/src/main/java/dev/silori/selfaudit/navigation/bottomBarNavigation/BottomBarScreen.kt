package dev.silori.selfaudit.navigation.bottomBarNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen (
    val route : String,
    val title : String,
    val icon : ImageVector
){

    object Audit : BottomBarScreen(
        route = "audit_screen",
        title = "Audit",
        icon = Icons.Default.SelfImprovement
    )
    object Insight : BottomBarScreen(
        route = "insight_screen",
        title = "Insight",
        icon = Icons.Default.Insights
    )

}