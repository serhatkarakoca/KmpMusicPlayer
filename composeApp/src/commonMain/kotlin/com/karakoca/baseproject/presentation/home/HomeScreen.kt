package com.karakoca.baseproject.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = koinViewModel()
    val loadingState by viewModel.loadingState.collectAsStateWithLifecycle()

    LaunchedEffect(loadingState) {
        viewModel.setLoadingState(loadingState)
    }

    HomeScreenMain(viewModel)
}

@Composable
fun HomeScreenMain(viewModel: HomeViewModel) {
    val gridState = rememberLazyGridState()
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 170.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = gridState
        ) {
            itemsIndexed(state.movies, key = { it, it2 -> it2.id ?: it }) { index, item ->
                MovieItem(item) { movie ->
                    if (movie.isFavorite)
                        viewModel.upsert(movie)
                    else
                        viewModel.deleteFav(movie)
                }

                if (index >= state.movies.lastIndex && !state.isLoading && !state.loadFinished)
                    LaunchedEffect(Unit, block = { viewModel.setEvent(HomeEvent.GetMovies) })
            }
        }

    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}