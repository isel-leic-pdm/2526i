package pt.isel.pdm.pokemonoftheday.services

import pt.isel.pdm.pokemonoftheday.domain.PokemonData

interface PokedexService {
    suspend fun getPokemonOfTheDay(): PokemonData
}