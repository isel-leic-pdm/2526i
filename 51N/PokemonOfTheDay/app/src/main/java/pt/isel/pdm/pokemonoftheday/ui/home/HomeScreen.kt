package pt.isel.pdm.pokemonoftheday.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pt.isel.pdm.pokemonoftheday.R
import pt.isel.pdm.pokemonoftheday.domain.PokemonData
import pt.isel.pdm.pokemonoftheday.domain.heightInMeter
import pt.isel.pdm.pokemonoftheday.domain.weightInKilogram
import pt.isel.pdm.pokemonoftheday.ui.about.AboutActivity
import pt.isel.pdm.pokemonoftheday.ui.common.CustomAppTopBar
import pt.isel.pdm.pokemonoftheday.ui.common.FullScreenPokemonDataView
import pt.isel.pdm.pokemonoftheday.ui.common.NavigationActions
import pt.isel.pdm.pokemonoftheday.ui.theme.PokemonOfTheDayTheme


@Composable
fun HomeScreen(
    navToAbout: () -> Unit,
    navToFirestorePlayground: () -> Unit,
    navToRoomPlayground: () -> Unit,
    viewModel: HomeViewModel
) {

    val secondPokemon = viewModel.secondPokemon.collectAsState()
    PokemonOfTheDayTheme {
        Scaffold(
            topBar = {
                CustomAppTopBar(
                    navActions = NavigationActions(
                        onAboutAction = navToAbout,
                        onFirestorePlayground = navToFirestorePlayground,
                        onRoomPlayground = navToRoomPlayground
                    )
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                when (val state = viewModel.screenState) {
                    is HomeViewState.ValidPokemon -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            FavouriteButton(state.isFav, onClick = {
                                viewModel.setCurrentAsFavourite()
                            })
                            FullScreenPokemonDataView(state.pokemon)

                            if (secondPokemon != PokemonData.None)
                                FullScreenPokemonDataView(secondPokemon.value)


                        }
                        Button(onClick = { viewModel.refreshPokemonOfTheDay() }) {
                            Text("Refreshes ${viewModel.refreshes}")
                        }
                    }

                    is HomeViewState.Loading -> {
                        Text(stringResource(R.string.loading))
                    }

                    is HomeViewState.Error -> {
                        Column {

                            Button(onClick = {
                                viewModel.refreshPokemonOfTheDay()
                            }) {
                                Text(stringResource(R.string.retry))
                            }
                            Text(state.error.toString())
                        }
                    }

                    is HomeViewState.Empty -> {}
                }
            }
        }
    }
}

@Composable
fun FavouriteButton(isSet: Boolean, onClick: () -> Unit) {

    IconButton(
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "",
            tint = (if (isSet) Color.Yellow else Color.Gray),
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun HomeScreenOnlyMutableState(
    navToAbout: () -> Unit,
    viewModel: HomeViewModelOnlyMutableState
) {
    PokemonOfTheDayTheme {
        Scaffold(
            topBar = {
                CustomAppTopBar(
                    navActions = NavigationActions(
                        onAboutAction = navToAbout
                    )
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                if (viewModel.lastError != null) {
                    Column {
                        Button(onClick = {
                            viewModel.refreshPokemonOfTheDay()
                        }) {
                            Text(stringResource(R.string.retry))
                        }
                        Text(viewModel.lastError.toString())
                    }
                } else {

                    if (viewModel.isLoading)
                        Text(stringResource(R.string.loading))
                    else {
                        FullScreenPokemonDataView(viewModel.pokemonOfTheDay)
                        Button(onClick = { viewModel.refreshPokemonOfTheDay() }) { }
                    }
                }
            }
        }
    }
}
