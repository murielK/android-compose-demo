package com.example.pta.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pta.domain.core.EventBus
import com.example.pta.ui.core.navigation.Navigation
import com.example.pta.ui.core.navigation.NavigationDestination
import com.example.pta.ui.core.navigation.collectEvents
import com.example.pta.ui.feature.history.HistoryScreen
import com.example.pta.ui.feature.history.destination.HistoryScreenNavigationDestination
import com.example.pta.ui.feature.home.game.GameScreen
import com.example.pta.ui.feature.home.game.destination.GameScreenNavigationDestination
import com.example.pta.ui.feature.home.landing.LandingScreen
import com.example.pta.ui.feature.home.landing.destination.LandingScreenNavigationDestination
import com.example.pta.ui.feature.home.result.ResultScreen
import com.example.pta.ui.feature.home.result.destination.ResultScreenNavigationDestination

/**
 * Created by muriel on 26.05.2023..
 */

@Composable
fun BottomNavigationScreen(
    bottomNavigator: EventBus<Navigation>,
    navController: NavHostController = rememberNavController()
) {

    bottomNavigator.collectEvents(navHostController = navController)

    Scaffold(bottomBar = {
        androidx.compose.material.BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            listOf<NavigationDestination<out Any>>(
                LandingScreenNavigationDestination,
                HistoryScreenNavigationDestination
            ).forEach { route ->
                BottomNavigationItem(icon = {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = null,
                    )
                },

                    label = { Text(text = route.route()) },
                    selected = currentDestination?.hierarchy?.any { it.route == route.route() } == true,
                    onClick = {
                        navController.navigate(route.route()) {
                            if (currentDestination?.hierarchy?.any { it.route == GameScreenNavigationDestination.route() } == true) {
                                popUpTo(GameScreenNavigationDestination.route()) {
                                    inclusive = true
                                }
                            } else {
                                popUpTo(navController.graph.findStartDestination().id) {
                                }
                            }
                            launchSingleTop = true

                        }
                    })
            }
        }

    }) { padding ->
        NavHost(

            navController = navController,
            startDestination = LandingScreenNavigationDestination.route(),
            modifier = Modifier.padding(padding)
        ) {
            composable(HistoryScreenNavigationDestination.route()) {
                HistoryScreen()
            }

            composable(
                GameScreenNavigationDestination.route(),
                arguments = GameScreenNavigationDestination.arguments
            ) { backStackEntry ->
                val questionsIds =
                    backStackEntry.arguments?.getLongArray(GameScreenNavigationDestination.NAV_ARGUMENT_PARAM)
                        ?.toList() ?: emptyList()
                GameScreen(questionsIds)
            }
            composable(ResultScreenNavigationDestination.route()) {
                ResultScreen()
            }

            composable(LandingScreenNavigationDestination.route()) {
                LandingScreen()
            }
        }

    }
}