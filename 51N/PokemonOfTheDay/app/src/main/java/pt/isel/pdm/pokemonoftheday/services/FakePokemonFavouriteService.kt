package pt.isel.pdm.pokemonoftheday.services

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePokemonFavouriteService : PokemonFavouriteService {

    val mutableStateFlow = MutableStateFlow<Int?>(null)
    override val currentFavourite: Flow<Int?>
        get() = mutableStateFlow

    override suspend fun set(p: Int) {
        mutableStateFlow.value = p
    }

    override suspend fun clear() {
        //mutableStateFlow.emit(null)
        mutableStateFlow.value = null
    }
}