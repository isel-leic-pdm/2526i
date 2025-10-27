package pt.isel.pdm.pokemonoftheday.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.isel.pdm.pokemonoftheday.domain.PokemonData
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.services.PokedexService
import pt.isel.pdm.pokemonoftheday.services.Pokemons

sealed interface HomeViewState
{
    data object None : HomeViewState
    data object Loading : HomeViewState
    data class Error(val exception: Exception) : HomeViewState
    data class Content(val pokemon: PokemonData) : HomeViewState
}

class HomeViewModel(
    private val service: PokedexService
) : ViewModel() {

    var state by mutableStateOf<HomeViewState>(HomeViewState.None)

    fun refreshPokemonOfTheDay() {
        state = HomeViewState.Loading
        viewModelScope
            .launch {
                try {
                    state = HomeViewState.Content(service.getPokemonOfTheDay())
                }catch (e: Exception)
                {
                    state = HomeViewState.Error(e)
                }
            }
    }
}


class HomeViewModel2(
    private val service: PokedexService
) : ViewModel() {

    var pokemonOfTheDay by mutableStateOf<PokemonData>(PokemonData.None)

    var isLoading by mutableStateOf(false)

    var error by mutableStateOf<Exception?>(null)


    fun refreshPokemonOfTheDay() {
        isLoading = true
        error = null
        viewModelScope
            .launch {
                try {
                    pokemonOfTheDay = service.getPokemonOfTheDay()
                    isLoading = false
                } catch (e: Exception) {
                    error = e
                }
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