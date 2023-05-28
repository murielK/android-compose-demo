package com.example.pta.ui.feature.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pta.ui.feature.splash.veiwmodel.SplashScreenViewModel

/**
 * Created by muriel on 26.05.2023..
 */

@Composable
fun SplashScreen(viewModel: SplashScreenViewModel = hiltViewModel()) {

    Scaffold {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(text = "Splash screen...")
        }
    }

}