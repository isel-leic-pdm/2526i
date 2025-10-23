package pt.isel.pdm.pokemonoftheday.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.isel.pdm.pokemonoftheday.domain.PokemonData
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.services.PokedexService
import pt.isel.pdm.pokemonoftheday.services.Pokemons


sealed interface HomeViewState {
    data class ValidPokemon(val pokemon: PokemonData) : HomeViewState
    data class Error(val error: Exception) : HomeViewState
    data object Loading : HomeViewState
    data object Empty : HomeViewState
}

class HomeViewModel(
    private val service: PokedexService
) : ViewModel() {

    var screenState by mutableStateOf<HomeViewState>(HomeViewState.Empty)

    fun refreshPokemonOfTheDay() {
        screenState = HomeViewState.Loading
        viewModelScope.launch {
            try {
                screenState = HomeViewState.ValidPokemon(service.getPokemonOfTheDay())
            } catch (e: Exception) {
                screenState = HomeViewState.Error(e)
            }
        }
    }
}

class HomeViewModelOnlyMutableState(
    private val service: PokedexService
) : ViewModel() {

    var isLoading by mutableStateOf(false)
    var lastError by mutableStateOf<Exception?>(null)
    var pokemonOfTheDay by mutableStateOf<PokemonData>(PokemonData.None)


    fun refreshPokemonOfTheDay() {
        lastError = null
        isLoading = true
        viewModelScope.launch() {
            try {
                pokemonOfTheDay = service.getPokemonOfTheDay()
            } catch (e: Exception) {
                lastError = e
            } finally {
                isLoading = false
            }
        }
    }
}

class HomeViewModelFactory(
    private val service: PokedexService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return HomeViewModel(service) as T
    }
}