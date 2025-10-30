package pt.isel.pdm.pokemonoftheday.services

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

class FakeStorageService : StorageService {

    val _favourites = MutableStateFlow<Int?>(null)

    override val favourite: Flow<Int?>
        get() = _favourites

    override suspend fun setFavourite(pokemon: Int) {
        _favourites.value = pokemon

    }

    override suspend fun removeFavourite() {
        _favourites.value = null
    }
}