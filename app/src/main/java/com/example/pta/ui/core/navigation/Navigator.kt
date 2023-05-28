package com.example.pta.ui.core.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.example.pta.domain.core.BaseEventBus
import com.example.pta.domain.core.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
class Navigator @Inject constructor() : BaseEventBus<Navigation>()

@Composable
fun EventBus<Navigation>.collectEvents(
    navHostController: NavHostController,
    scope: CoroutineScope = rememberCoroutineScope(),
) {
    DisposableEffect(key1 = this) {
        val job = scope.launch {
            Log.e("Navigator", "LAUNCHED")
            events.collect { event ->
                when (event) {
                    Navigation.NavigateUp -> navHostController.navigateUp()
                    Navigation.NavigateBack -> navHostController.popBackStack()
                    is Navigation.Navigate -> navHostController.navigate(
                        route = event.route,
                        builder = event.navOptionsBuilder,
                    )
                }
            }
        }
        onDispose {
            job.cancel()
        }
    }
}