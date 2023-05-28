package com.example.pta.ui.core.navigation

import androidx.navigation.NamedNavArgument

/**
 * Created by muriel on 26.05.2023..
 */
interface NavigationDestination<T> {

    fun route(data: T? = null): String

    val arguments: List<NamedNavArgument>
}