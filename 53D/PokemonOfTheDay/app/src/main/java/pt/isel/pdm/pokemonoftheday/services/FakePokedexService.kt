package pt.isel.pdm.pokemonoftheday.services

import kotlinx.coroutines.delay
import pt.isel.pdm.pokemonoftheday.domain.PokemonData


class FakePokedexService : PokedexService {
    var idx = 0
    override suspend fun getPokemonOfTheDay(): PokemonData {
        delay(1000)

        if (idx > Pokemons.List.size)
            idx = 0

        return Pokemons.List[idx++]
    }
}


object Pokemons {
    val List = listOf(
        PokemonData(
            id = 1,
            name = "Bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            height = 7, // decimeter
            weight = 69 // hectograms
        ),
        PokemonData(
            id = 4,
            name = "Charmander",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
            height = 6, // decimeter
            weight = 85 // hectograms
        ),
        PokemonData(
            id = 7,
            name = "Squirtle",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png",
            height = 5, // decimeter
            weight = 90 // hectograms
        ),
        PokemonData(
            id = 25,
            name = "Pikachu",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png",
            height = 4, // decimeter
            weight = 60 // hectograms
        ),

        )

}
