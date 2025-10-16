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
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
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
                FullScreenPokemonDataView(viewModel.pokemonOfTheDay)
                Button(onClick = { viewModel.refreshPokemonOfTheDay() }) { }
            }
        }
    }
}
