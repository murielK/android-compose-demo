package com.example.pta.ui

import androidx.navigation.NamedNavArgument
import com.example.pta.ui.core.navigation.NavigationDestination

/**
 * Created by muriel on 26.05.2023..
 */
object BottomNavigationDestination : NavigationDestination<Nothing> {

    private const val ROUTE = "BottomNavigation"
    override fun route(data: Nothing?): String {
        return ROUTE
    }

    override val arguments: List<NamedNavArgument>
        get() = TODO("Not yet implemented")
}