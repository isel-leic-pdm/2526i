package pt.isel.pdm.pokemonoftheday.services

import kotlinx.coroutines.flow.Flow
import pt.isel.pdm.pokemonoftheday.domain.PokemonData

interface PokemonFavouriteHistoryService {
    suspend fun getHistory(): Flow<List<PokemonData>>

    suspend fun add(p: PokemonData)
    suspend fun remove(p: PokemonData)
}