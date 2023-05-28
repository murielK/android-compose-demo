package com.example.pta.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pta.domain.core.EventBus
import com.example.pta.ui.core.di.BottomNavigator
import com.example.pta.ui.core.di.MainNavigator
import com.example.pta.ui.core.navigation.Navigation
import com.example.pta.ui.feature.splash.destination.SplashScreenNavigationDestination
import com.example.pta.ui.theme.PersonalTestApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    @MainNavigator
    lateinit var navigator: EventBus<Navigation>

    @Inject
    @BottomNavigator
    lateinit var bottomNavigator: EventBus<Navigation>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PersonalTestApplicationTheme {
                MainApp(navigator, bottomNavigator, SplashScreenNavigationDestination.route())
            }
        }
    }
}
