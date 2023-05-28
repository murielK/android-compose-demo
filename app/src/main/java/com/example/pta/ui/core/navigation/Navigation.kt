package com.example.pta.ui.core.navigation

import androidx.navigation.NavOptionsBuilder

/**
 * Created by muriel on 26.05.2023..
 */
sealed class Navigation {

    object NavigateUp : Navigation()

    object NavigateBack : Navigation()

    data class Navigate(
        val route: String,
        val navOptionsBuilder: NavOptionsBuilder.() -> Unit = {}
    ) :
        Navigation()
}