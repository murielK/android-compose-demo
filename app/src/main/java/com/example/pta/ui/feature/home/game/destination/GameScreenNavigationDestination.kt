package com.example.pta.ui.feature.home.game.destination

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.pta.ui.core.navigation.NavigationDestination
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by muriel on 26.05.2023..
 */
object GameScreenNavigationDestination : NavigationDestination<List<Long>> {

    const val NAV_ARGUMENT_PARAM = "ids"
    private const val ROUTE = "game/{$NAV_ARGUMENT_PARAM}"

    override fun route(data: List<Long>?): String {
        return if (data == null) ROUTE else "game/${Gson().toJson(data)}"
    }

    override val arguments: List<NamedNavArgument>
        get() = listOf(navArgument(NAV_ARGUMENT_PARAM) {
            type = IdsNavType()
        })

    class IdsNavType : NavType<List<Long>>(isNullableAllowed = false) {

        override fun get(bundle: Bundle, key: String): List<Long>? {
            return bundle.getLongArray(key)?.toList()
        }

        override fun parseValue(value: String): List<Long> {
            return Gson().fromJson(value, object : TypeToken<List<Long>>() {}.type)
        }

        override fun put(bundle: Bundle, key: String, value: List<Long>) {
            bundle.putLongArray(key, value.toLongArray())
        }
    }
}