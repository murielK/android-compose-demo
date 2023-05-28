package com.example.pta.ui.feature.home.result.veiwmodel

import com.example.pta.domain.core.EventBus
import com.example.pta.ui.core.base.BaseViewModel
import com.example.pta.ui.core.di.BottomNavigator
import com.example.pta.ui.core.navigation.Navigation
import com.example.pta.ui.feature.home.landing.destination.LandingScreenNavigationDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.toDuration

/**
 * Created by muriel on 26.05.2023..
 */
@HiltViewModel
class ResultViewModel @Inject constructor(@BottomNavigator private val navigator: EventBus<Navigation>) :
    BaseViewModel() {

    init {
        launch {
            delay(5.toDuration(DurationUnit.SECONDS))
            navigator.postEvent(Navigation.Navigate(LandingScreenNavigationDestination.route()) {
                popUpTo(LandingScreenNavigationDestination.route()) {
                    inclusive = true
                }
            })
        }
    }

}