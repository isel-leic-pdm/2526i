package pt.isel.pdm.pokemonoftheday.services

import kotlinx.coroutines.flow.Flow
import pt.isel.pdm.pokemonoftheday.domain.PokemonData

interface PokemonFavouriteHistoryService {
    suspend fun add(p: PokemonData)
    suspend fun getAllHistory(): Flow<List<PokemonData>>
    suspend fun delete(p: PokemonData)
}