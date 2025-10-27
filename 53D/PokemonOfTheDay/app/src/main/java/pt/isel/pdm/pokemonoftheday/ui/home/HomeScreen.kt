package pt.isel.pdm.pokemonoftheday.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    viewModel: HomeViewModel
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


                when (val state = viewModel.state) {
                    is HomeViewState.Content -> {
                        FullScreenPokemonDataView(state.pokemon)
                        Button(onClick = { viewModel.refreshPokemonOfTheDay() }) { }
                    }

                    is HomeViewState.Loading -> {
                        Text(stringResource(R.string.Loading))
                    }

                    is HomeViewState.Error -> {
                        Column {
                            Button(onClick = {
                                viewModel.refreshPokemonOfTheDay()
                            })
                            {
                                Text(stringResource(R.string.retry))
                            }
                            Text(state.exception.toString())
                        }
                    }

                    else -> {

                    }
                }
            }


        }
    }
}

@Composable
fun HomeScreen2(
    navToAbout: () -> Unit,
    viewModel: HomeViewModel2
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
                if (viewModel.error != null) {
                    Button(onClick = {
                        viewModel.refreshPokemonOfTheDay()
                    }) {
                        Text(stringResource(R.string.retry))
                    }
                    Text(viewModel.error.toString())

                }
                if (viewModel.isLoading) {
                    Text(stringResource(R.string.Loading))
                } else {
                    FullScreenPokemonDataView(viewModel.pokemonOfTheDay)
                    Button(onClick = { viewModel.refreshPokemonOfTheDay() }) { }
                }
            }
        }
    }
}
