package pt.isel.pdm.pokemonoftheday.services

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pt.isel.pdm.pokemonoftheday.domain.PokemonData


class FakePokedexService : PokedexService {
    var idx = 0
    private var throwErrorOnAllRequests = false

    override suspend fun getPokemonOfTheDay(): PokemonData {
        delay(1000)

        if (throwErrorOnAllRequests)
            throw Exception("Forced Error")

        if (idx > Pokemons.List.size)
            idx = 0

        return Pokemons.List[idx++]
    }

    override fun getPokemonOfTheSecondFlow(): Flow<PokemonData> {
        return flow {
            while (true) {
                if (throwErrorOnAllRequests)
                    throw Exception("Forced Error")

                emit(Pokemons.List[(0..Pokemons.List.size - 1).random()])
                delay(1000)
            }
        }
    }

    fun setErrorOnAllRequests() {
        throwErrorOnAllRequests = true
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
