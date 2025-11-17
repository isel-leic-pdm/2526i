package pt.isel.pdm.pokemonoftheday.ui.home

import android.R
import android.util.Log
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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pt.isel.pdm.pokemonoftheday.domain.PokemonData
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.services.PokedexService
import pt.isel.pdm.pokemonoftheday.services.PokemonFavouriteHistoryService
import pt.isel.pdm.pokemonoftheday.services.PokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.services.Pokemons

sealed interface HomeViewState {
    data object None : HomeViewState
    data object Loading : HomeViewState
    data class Error(val exception: Exception) : HomeViewState
    data class Content(val pokemon: PokemonData, val isFav: Boolean) : HomeViewState
}

class HomeViewModel(
    private val service: PokedexService,
    private val favouriteService: PokemonFavouriteService,
    private val favouriteHistoryService: PokemonFavouriteHistoryService
) : ViewModel() {

    var state by mutableStateOf<HomeViewState>(HomeViewState.None)

    var pokemonOfTheSecond = service.getPokemonOfTheSecondFlow().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        PokemonData.None
    )

    fun refreshPokemonOfTheDay() {
        state = HomeViewState.Loading
        viewModelScope
            .launch {
                try {
                    val p = service.getPokemonOfTheDay()
                    val isFav = favouriteService.favourite.first() == p.id
                    state = HomeViewState.Content(p, isFav)
                } catch (e: Exception) {
                    state = HomeViewState.Error(e)
                }
            }
    }

    fun toggleFavourite() {
        viewModelScope.launch {
            val screenState = state
            if (screenState is HomeViewState.Content) {
                if (screenState.isFav) {
                    favouriteService.clearFavourite()
                    state = HomeViewState.Content(screenState.pokemon, false)
                } else {
                    favouriteService.setFavourite(screenState.pokemon.id)
                    state = HomeViewState.Content(screenState.pokemon, true)
                    favouriteHistoryService.add(screenState.pokemon)

                }
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
        return HomeViewModel2(service) as T
    }
}