package pt.isel.pdm.pokemonoftheday.services

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import pt.isel.pdm.pokemonoftheday.domain.PokemonData

class FirestorePokedexService(
    private val db: FirebaseFirestore
) : PokedexService {

    val POKEMON_MAX_ID = 151
    val POKEMON_COLLECTION = "pokemons"

    override suspend fun getPokemonOfTheDay(): PokemonData {
        val id = (1..POKEMON_MAX_ID).random()


        return getPokemonById(id)

    }

    private suspend fun getPokemonById(id: Int): PokemonData {
        val docSnapshot = db.collection(POKEMON_COLLECTION)
            .document(id.toString())
            .get()
            .await()

        return docSnapshot
            .toObject(PokemonDataDto::class.java)!!
            .toPokemonData()
    }

    override fun getPokemonOfTheSecondFlow(): Flow<PokemonData> {

        return flow {
            while (true) {
                val id = (0..POKEMON_MAX_ID).random()
                val poke = getPokemonById(id)
                emit(poke)
                delay(1000)
            }

        }


    }
}


data class PokemonDataDto(
    val id: Int = -1,
    val name: String = "",
    val imageUrl: String = "",
    val height: Int = -1, // decimeter
    val weight: Int = -1 // hectograms
) {
    fun toPokemonData(): PokemonData {
        return PokemonData(
            id = id,
            name = name,
            imageUrl = imageUrl,
            height = height,
            weight = weight
        )
    }
}
