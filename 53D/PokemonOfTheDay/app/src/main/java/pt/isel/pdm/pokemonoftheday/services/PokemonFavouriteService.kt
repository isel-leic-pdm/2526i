package pt.isel.pdm.pokemonoftheday.services

import kotlinx.coroutines.flow.Flow

interface PokemonFavouriteService {
    val favourite: Flow<Int?>
    suspend fun setFavourite(id:Int)
    suspend fun clearFavourite()
}