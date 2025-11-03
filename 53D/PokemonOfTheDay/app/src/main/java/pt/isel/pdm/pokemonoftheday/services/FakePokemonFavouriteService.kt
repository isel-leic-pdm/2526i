package pt.isel.pdm.pokemonoftheday.services

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePokemonFavouriteService : PokemonFavouriteService {

    private val fav = MutableStateFlow<Int?>(null)

    override val favourite: Flow<Int?>
        get() = fav

    override suspend fun setFavourite(id: Int) {
        fav.value = id
    }

    override suspend fun clearFavourite() {
        fav.value = null
    }
}