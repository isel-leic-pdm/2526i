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

    val MAX_POKEMON_NR = 1000

    override suspend fun getPokemonOfTheDay(): PokemonData {
        val id = (LocalDate.now().dayOfYear + LocalDate.now().year) % MAX_POKEMON_NR

        return getPokemonById(id)
    }

    suspend fun getPokemonById(id: Int): PokemonData {
        val resp = cli.get("https://pokeapi.co/api/v2/pokemon/$id")
        val pokeData = resp.body<PokeApiPokemonDataDto>()

        return pokeData.toPokemonData()
    }

    override fun getPokemonOfTheSecondFlow(): Flow<PokemonData> {
        return flow {
            while (true) {
                emit(getPokemonById((1..MAX_POKEMON_NR).random()))
                delay(1000)
            }

        }
    }
}

@Serializable
data class PokeApiPokemonDataDto(
    val id: Int,
    val name: String,
    val sprites: PokeApiSpritesDto,
    val height: Int, // decimeter
    val weight: Int  // hectograms
) {

    fun toPokemonData(): PokemonData {
        return PokemonData(
            id, name, sprites.frontSprite, height, weight
        )
    }
}

@Serializable
data class PokeApiSpritesDto(
    @SerialName("front_default")
    val frontSprite: String
) {
}
