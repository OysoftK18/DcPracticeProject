package com.example.dcpracticeproject.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dcpracticeproject.boldAndNormal
import com.example.dcpracticeproject.models.CardDB

@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    val databaseUiState by viewModel.databaseUiState.collectAsState()
    Column {

        if (databaseUiState.cardList.isNotEmpty()) {
            if (viewModel.heroesList.isNotEmpty()) {
                PlayerDisplayed(viewModel.heroesList)
            } else {
                Button(onClick = {
                    viewModel.shuffleMainHeroes()
                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Get Players HEROES")
                }
            }
        } else {
            when (viewModel.fetchUiState) {
                is FetchUiState.Loading -> {
                    LoadingScreen()
                    viewModel.retrieveDataOnlineAndPopulateDatabase()
                }

                FetchUiState.Failed -> FailedScreen()
                else -> null
            }
        }
    }
}

@Composable
fun FailedScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Failed")
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

/**Function to display everything that is in the database if needed */
@Composable
fun CardListScreen(cardList: List<CardDB>) {
    LazyColumn() {
        items(cardList) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(2.dp),
                elevation = CardDefaults.elevatedCardElevation(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(it.url)
                            .crossfade(true).build(),
                        contentDescription = it.name,
                        modifier = Modifier
                            .width(60.dp)
                            .fillMaxHeight()
                    )
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = boldAndNormal("Name: ", it.name))
                        Text(text = boldAndNormal("Set: ", it.set))
                        Text(text = boldAndNormal("Tier: ", it.tier))
                    }
                }
            }
        }
    }
}

@Composable
fun PlayerDisplayed(heroesList: List<List<CardDB>>) {
    LazyColumn() {
        items(heroesList) {
            it.forEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(it.url)
                            .crossfade(true).build(),
                        contentDescription = it.name,
                        modifier = Modifier
                            .width(60.dp)
                            .fillMaxHeight()
                    )
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = boldAndNormal("Name: ", it.name))
                        Text(text = boldAndNormal("Set: ", it.set))
                        Text(text = boldAndNormal("Tier: ", it.tier))
                    }
                }
            }
        }
    }
}