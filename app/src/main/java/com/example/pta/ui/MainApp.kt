package com.example.pta.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pta.domain.core.EventBus
import com.example.pta.ui.core.navigation.Navigation
import com.example.pta.ui.core.navigation.collectEvents
import com.example.pta.ui.feature.splash.SplashScreen
import com.example.pta.ui.feature.splash.destination.SplashScreenNavigationDestination

/**
 * Created by muriel on 26.05.2023..
 */

@Composable
fun MainApp(
    mainNavigator: EventBus<Navigation>,
    bottomNavigator: EventBus<Navigation>,
    startDestination: String,
    navController: NavHostController = rememberNavController()
) {

    mainNavigator.collectEvents(navHostController = navController)

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(BottomNavigationDestination.route()) {
            BottomNavigationScreen(bottomNavigator = bottomNavigator)
        }
        composable(SplashScreenNavigationDestination.route()) {
            SplashScreen()
        }
    }
}
