package pt.isel.pdm.pokemonoftheday.services

import kotlinx.coroutines.flow.Flow
import pt.isel.pdm.pokemonoftheday.domain.PokemonData

interface PokemonFavouriteService {

    val currentFavourite: Flow<Int?>
    suspend fun set(p: Int)
    suspend fun clear()

}