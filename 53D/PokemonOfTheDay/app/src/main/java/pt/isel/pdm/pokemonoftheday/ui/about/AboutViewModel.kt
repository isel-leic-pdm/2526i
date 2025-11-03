package pt.isel.pdm.pokemonoftheday.ui.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import pt.isel.pdm.pokemonoftheday.services.PokemonFavouriteService

class AboutViewModel(
    favouriteService: PokemonFavouriteService
) : ViewModel() {


    val quickFav = favouriteService.favourite.
    stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = null
    )

    private val favouriteInternal = MutableStateFlow<Int?>(null)

    val favourite: StateFlow<Int?> = favouriteInternal


    init {
        viewModelScope.launch {
            favouriteService.favourite.collect {
                favouriteInternal.value = it
            }
        }
    }

}