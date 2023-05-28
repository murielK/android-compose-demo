@file:JvmName("HistoryViewModelKt")

package com.example.pta.ui.feature.history

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pta.ui.core.LoadingState
import com.example.pta.ui.core.component.ListDivider
import com.example.pta.ui.feature.history.viewmodel.HistoryViewModel

/**
 * Created by muriel on 26.05.2023..
 */

@Composable
fun HistoryScreen(viewModel: HistoryViewModel = hiltViewModel()) {

    val historiesState by viewModel.historiesState.collectAsState()
    val loadingState by viewModel.loadingState.collectAsState()


    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

        AnimatedVisibility(
            visible = loadingState == LoadingState.Loading,
            modifier = Modifier.fillMaxSize(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        AnimatedVisibility(
            visible = loadingState != LoadingState.Loading && historiesState.isEmpty(),
            modifier = Modifier.fillMaxSize(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "No histories for now...start a game first and check again later",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(64.dp)
                )
            }
        }

        AnimatedVisibility(
            visible = loadingState == LoadingState.Idle,
            modifier = Modifier.fillMaxSize(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(32.dp)
            ) {
                items(count = historiesState.size) {
                    Text(text = historiesState[it].timestamp)
                    ListDivider()
                }
            }
        }
    }
}