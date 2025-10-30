package pt.isel.pdm.pokemonoftheday.services

import kotlinx.coroutines.flow.Flow
import pt.isel.pdm.pokemonoftheday.domain.PokemonData

interface StorageService {
    val favourite: Flow<Int?>

    suspend fun setFavourite(pokemon: Int)
    suspend fun removeFavourite()

}

