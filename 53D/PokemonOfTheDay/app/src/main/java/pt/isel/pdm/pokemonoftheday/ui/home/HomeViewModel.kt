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
import pt.isel.pdm.pokemonoftheday.services.Pokemons
import pt.isel.pdm.pokemonoftheday.services.StorageService

sealed interface HomeViewState {
    data object None : HomeViewState
    data object Loading : HomeViewState
    data class Error(val exception: Exception) : HomeViewState
    data class Content(val pokemon: PokemonData, val isFav: Boolean) : HomeViewState
}

class HomeViewModel(
    private val service: PokedexService,
    private val storageService: StorageService
) : ViewModel() {

    var state by mutableStateOf<HomeViewState>(HomeViewState.None)


    fun refreshPokemonOfTheDay() {
        state = HomeViewState.Loading
        viewModelScope
            .launch {
                try {
                    val fav  = storageService.favourite.first()
                    val p = service.getPokemonOfTheDay()
                    state = HomeViewState.Content(p, fav == p.id)
                } catch (e: Exception) {
                    state = HomeViewState.Error(e)
                }
            }
    }

    fun markAsFavourite() {
        viewModelScope.launch {
            val s = state
            if (s is HomeViewState.Content && s.isFav == false) {
                storageService.setFavourite(s.pokemon.id)
                state = s.copy(isFav = true)
            }
        }
    }

    fun unmarkAsFavourite() {
        viewModelScope.launch {
            val s = state
            if (s is HomeViewState.Content && s.isFav) {
                storageService.removeFavourite()
                state = s.copy(isFav = false)
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