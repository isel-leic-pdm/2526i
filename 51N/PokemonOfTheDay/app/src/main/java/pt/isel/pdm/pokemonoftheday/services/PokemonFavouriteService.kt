package pt.isel.pdm.pokemonoftheday.services

import kotlinx.coroutines.flow.Flow

interface PokemonFavouriteService {

    val currentFavourite: Flow<Int?>
    suspend fun set(p: Int)
    suspend fun clear()
}