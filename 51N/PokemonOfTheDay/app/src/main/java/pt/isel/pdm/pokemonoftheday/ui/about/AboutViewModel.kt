package pt.isel.pdm.pokemonoftheday.ui.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pt.isel.pdm.pokemonoftheday.services.PokemonFavouriteService

class AboutViewModel(
    pokemonFavouriteService: PokemonFavouriteService
) : ViewModel() {


    val currentFavFlow = pokemonFavouriteService.currentFavourite
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            null
        )

    /*
    var currentFav: Int? = 0

    init {
        viewModelScope.launch {
            pokemonFavouriteService.currentFavourite.collect {
                currentFav = it
            }
        }
    }
    */
}