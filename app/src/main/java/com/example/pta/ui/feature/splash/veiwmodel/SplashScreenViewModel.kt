package com.example.pta.ui.feature.splash.veiwmodel

import com.example.pta.domain.core.EventBus
import com.example.pta.domain.core.ptaDefaultDelay
import com.example.pta.ui.BottomNavigationDestination
import com.example.pta.ui.core.base.BaseViewModel
import com.example.pta.ui.core.di.MainNavigator
import com.example.pta.ui.core.navigation.Navigation
import com.example.pta.ui.feature.splash.destination.SplashScreenNavigationDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by muriel on 26.05.2023..
 */
@HiltViewModel
class SplashScreenViewModel @Inject constructor(@MainNavigator private val navigator: EventBus<Navigation>) :
    BaseViewModel() {

    init {
        launch {
            ptaDefaultDelay()
            navigator.postEvent(Navigation.Navigate(BottomNavigationDestination.route()) {
                popUpTo(SplashScreenNavigationDestination.route()) {
                    inclusive = true
                }
            })
        }
    }

}