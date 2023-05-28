package com.example.pta.ui.feature.splash.destination

import androidx.navigation.NamedNavArgument
import com.example.pta.ui.core.navigation.NavigationDestination

/**
 * Created by muriel on 26.05.2023..
 */
object SplashScreenNavigationDestination : NavigationDestination<Nothing> {

    private const val ROUTE = "splash"

    override fun route(data: Nothing?): String {
        return ROUTE
    }

    override val arguments: List<NamedNavArgument>
        get() = TODO("Not yet implemented")
}