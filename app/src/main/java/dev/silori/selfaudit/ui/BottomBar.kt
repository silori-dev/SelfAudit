package dev.silori.selfaudit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.silori.selfaudit.navigation.bottomBarNavigation.BottomBarScreen

@Composable
fun BottomBar(
    navController: NavController
) {
    val screens = listOf(
        BottomBarScreen.Audit,
        BottomBarScreen.Insight,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                navController = navController,
                currentDestination = currentDestination,
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    navController: NavController,
    currentDestination: NavDestination?
) {
    val selected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } == true

    NavigationItem(
        label = {
            Text(text = screen.title)
        },
        selected = selected,
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = null
            )
        },
        onClickEvent = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
    )
}