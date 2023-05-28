package com.example.pta.ui.feature.home.game

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pta.ui.core.LoadingState
import com.example.pta.ui.core.component.ListDivider
import com.example.pta.ui.feature.home.game.event.GameScreenEvent
import com.example.pta.ui.feature.home.game.state.GameState
import com.example.pta.ui.feature.home.game.veiwmodel.GameScreenViewModel

/**
 * Created by muriel on 26.05.2023..
 */

@Composable
fun GameScreen(questionsIds: List<Long>, viewModel: GameScreenViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.onEvent(GameScreenEvent.OnQuestionsIds(questionsIds))
    }

    val loadingState by viewModel.loadingState.collectAsState()
    val gameState by viewModel.gameState.collectAsState()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        val (emptyText, progress) = createRefs()

        AnimatedVisibility(
            visible = loadingState == LoadingState.Loading,
            modifier = Modifier.constrainAs(progress) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CircularProgressIndicator()
        }

        AnimatedVisibility(
            visible = gameState == GameState.Empty && loadingState != LoadingState.Loading,
            modifier = Modifier.constrainAs(emptyText) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            Text(
                text = "No questions yet...", modifier = Modifier.size(150.dp)
            )
        }

        AnimatedVisibility(
            visible = gameState is GameState.Question,
            modifier = Modifier.fillMaxSize(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            ConstraintLayout(modifier = Modifier.fillMaxSize()) {

                val (textQuestion, lazyColumn, button1, button2) = createRefs()

                Text(fontSize = 20.sp,
                    text = (gameState as GameState.Question).questionUi.question,
                    modifier = Modifier.constrainAs(textQuestion) {
                        top.linkTo(parent.top)
                    })

                LazyColumn(
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                        .constrainAs(lazyColumn) {
                            top.linkTo(textQuestion.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(button1.top)
                            height = Dimension.fillToConstraints
                            width = Dimension.matchParent
                        }, verticalArrangement = Arrangement.Center
                ) {

                    val items = (gameState as GameState.Question).answers
                    items(items.size) { index ->
                        ItemView(
                            index,
                            items[index].answer,
                            index == (gameState as GameState.Question).selectedIndex
                        ) {
                            viewModel.onAnswer(it)
                        }
                        ListDivider()
                    }
                }

                Button(
                    modifier = Modifier.constrainAs(button1) {
                        start.linkTo(parent.start)
                        end.linkTo(button2.start, margin = 16.dp)
                        bottom.linkTo(parent.bottom)
                    },
                    onClick = { viewModel.onGoBackClicked() },
                    enabled = (gameState as GameState.Question).canGoBack
                ) {
                    Text(text = "Back")
                }

                Button(modifier = Modifier.constrainAs(button2) {
                    start.linkTo(button1.end, margin = 16.dp)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }, onClick = { viewModel.onNextClicked() },
                    enabled = (gameState as GameState.Question).canGoNext
                ) {
                    Text(text = "Next")
                }

                createHorizontalChain(button1, button2, chainStyle = ChainStyle.Packed)
            }

        }
    }
}

@Composable
fun ItemView(index: Int, text: String, selected: Boolean, onClick: (Int) -> Unit) {
    Text(
        text = text, modifier = Modifier
            .clickable {
                onClick.invoke(index)
            }
            .background(if (selected) MaterialTheme.colors.secondary else Color.Transparent)
            .fillMaxWidth(), textAlign = TextAlign.Center)
}