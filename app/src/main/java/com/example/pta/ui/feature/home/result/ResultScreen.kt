package com.example.pta.ui.feature.home.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.pta.R
import com.example.pta.ui.feature.home.result.veiwmodel.ResultViewModel


/**
 * Created by muriel on 26.05.2023..
 */

@Composable
fun ResultScreen(viewModel: ResultViewModel = hiltViewModel()) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.demo_lottie))
    val progress by animateLottieCompositionAsState(composition)

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            LottieAnimation(
                composition = composition,
                progress = { progress },
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Wooooow You are wonderful! Thank You!")
        }
    }
}