package pt.isel.pdm.pokemonoftheday.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.isel.pdm.pokemonoftheday.domain.PokemonData
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.services.PokedexService
import pt.isel.pdm.pokemonoftheday.services.Pokemons

class HomeViewModel(
    private val service: PokedexService
) : ViewModel() {

    var pokemonOfTheDay by mutableStateOf<PokemonData>(PokemonData.None)

    fun refreshPokemonOfTheDay() {
        CoroutineScope(Dispatchers.Default)
            .launch {
                pokemonOfTheDay = service.getPokemonOfTheDay()
            }
    }
}

class HomeViewModelFactory(
    val service: PokedexService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(service) as T
    }
}