package com.example.pta.ui.feature.home.landing

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pta.ui.core.LoadingState
import com.example.pta.ui.feature.home.landing.veiwmodel.LandingScreenViewModel

/**
 * Created by muriel on 26.05.2023..
 */

@Composable
fun LandingScreen(viewModel: LandingScreenViewModel = hiltViewModel()) {

    val loadingState by viewModel.loadingState.collectAsState()

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

        AnimatedVisibility(visible = loadingState == LoadingState.Idle) {
            Button(onClick = { viewModel.onStartClicked() }) {
                Text(text = "Start PTA Test")
            }
        }

        AnimatedVisibility(visible = loadingState == LoadingState.Loading) {
            CircularProgressIndicator()
        }
    }
}