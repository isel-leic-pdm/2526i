package pt.isel.pdm.pokemonoftheday.services

import kotlinx.coroutines.flow.Flow
import pt.isel.pdm.pokemonoftheday.domain.PokemonData

interface PokedexService {
    suspend fun getPokemonOfTheDay(): PokemonData
    val pokemonOfTheSecond : Flow<PokemonData>
}