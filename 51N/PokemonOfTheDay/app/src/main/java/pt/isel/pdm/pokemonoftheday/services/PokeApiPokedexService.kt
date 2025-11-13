package pt.isel.pdm.pokemonoftheday.services

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import pt.isel.pdm.pokemonoftheday.domain.PokemonData
import java.time.LocalDate

class PokeApiPokedexService(
    private val cli: HttpClient
) : PokedexService {

    val MAX_POKEMON_NUMBER = 1000

    override suspend fun getPokemonOfTheDay(): PokemonData {
        val id = (LocalDate.now().dayOfYear + LocalDate.now().year) % MAX_POKEMON_NUMBER
        return getPokemonById(id)
    }

    private suspend fun getPokemonById(id: Int): PokemonData {
        val resp = cli.get("https://pokeapi.co/api/v2/pokemon/$id")

        val result = resp.body<PokeApiPokemonData>()
        return result.toPokemonData()
    }

    override val pokemonOfTheSecond: Flow<PokemonData> = flow {
        while (true) {
            emit(getPokemonById((1..MAX_POKEMON_NUMBER).random()))
            delay(1000)
        }
    }
}

fun PokeApiPokemonData.toPokemonData(): PokemonData =
    PokemonData(id, name, sprites.sprite, height, weight)

@Serializable
data class PokeApiPokemonData(
    val id: Int, val name: String, val height: Int, // decimeter
    val weight: Int,  // hectograms
    val sprites: PokeApiSpriteData
) {}

@Serializable
data class PokeApiSpriteData(
    @SerialName("front_default")
    val sprite: String
)
