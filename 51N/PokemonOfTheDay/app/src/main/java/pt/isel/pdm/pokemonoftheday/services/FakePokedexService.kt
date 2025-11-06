package pt.isel.pdm.pokemonoftheday.services

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pt.isel.pdm.pokemonoftheday.domain.PokemonData
import java.time.LocalTime


class FakePokedexService : PokedexService {

    var currIdx = 0
    override suspend fun getPokemonOfTheDay(): PokemonData {
        delay(1000)

        if (currIdx > Pokemons.List.size)
            currIdx = 0

        return Pokemons.List[++currIdx]
    }

    override val pokemonOfTheSecond: Flow<PokemonData> = flow{
        while (true){
            val id = LocalTime.now().second % Pokemons.List.size
            emit(Pokemons.List[id])
            delay(1000)
        }
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
