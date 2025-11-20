package pt.isel.pdm.pokemonoftheday.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.saveable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
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
    data class ValidPokemon(val pokemon: PokemonData, val isFav: Boolean) : HomeViewState
    data class Error(val error: Exception) : HomeViewState
    data object Loading : HomeViewState
    data object Empty : HomeViewState
}

class HomeViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val service: PokedexService,
    private val pokemonFavouriteService: PokemonFavouriteService,
    private val pokemonFavouriteHistoryService: PokemonFavouriteHistoryService
) : ViewModel() {

    //var refreshes by mutableStateOf(savedStateHandle.get("refreshes") ?: 0)

    var refreshes by savedStateHandle.saveable {
        mutableStateOf(0)
    }

    var screenState by mutableStateOf<HomeViewState>(HomeViewState.Empty)


    val secondPokemon = service.pokemonOfTheSecond
        .catch { } //PokeApi sometimes returns 500, this avois any crashes, Hammer
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            PokemonData.None
        )

    fun refreshPokemonOfTheDay() {
        screenState = HomeViewState.Loading

        refreshes++
        //savedStateHandle.set("refreshes", refreshes)

        viewModelScope.launch {
            try {
                val pokemon = service.getPokemonOfTheDay()

                val currFav = pokemonFavouriteService.currentFavourite.first()

                screenState = HomeViewState.ValidPokemon(pokemon, currFav == pokemon.id)
            } catch (e: Exception) {
                screenState = HomeViewState.Error(e)
            }
        }
    }


    fun setCurrentAsFavourite() {
        viewModelScope.launch {
            val state = screenState
            if (state is HomeViewState.ValidPokemon) {

                val isCurrFav = state.pokemon.id == pokemonFavouriteService.currentFavourite.first()
                if (isCurrFav)
                    pokemonFavouriteService.clear()
                else {
                    pokemonFavouriteService.set(state.pokemon.id)
                    pokemonFavouriteHistoryService.add(state.pokemon)
                }

                screenState = HomeViewState.ValidPokemon(state.pokemon, !isCurrFav)

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
/*
class HomeViewModelFactory(
    private val service: PokedexService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return HomeViewModel2(service) as T
    }
}*/