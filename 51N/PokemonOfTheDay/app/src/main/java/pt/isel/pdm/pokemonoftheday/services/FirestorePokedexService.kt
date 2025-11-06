package pt.isel.pdm.pokemonoftheday.services

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import pt.isel.pdm.pokemonoftheday.domain.PokemonData
import java.time.LocalDate
import java.time.LocalTime

class FirestorePokedexService(
    private val db: FirebaseFirestore
) : PokedexService {

    private val POKEMONS_COLLECTION = "pokemons"
    private val POKEMON_MAX = 151

    override suspend fun getPokemonOfTheDay(): PokemonData {
        val id = ((LocalDate.now().dayOfYear + LocalDate.now().year
                * LocalTime.now().second) % POKEMON_MAX) + 1

        return getPokemon(id)

    }


    override val pokemonOfTheSecond: Flow<PokemonData> = flow {
        while (true) {
            val id = (LocalTime.now().toSecondOfDay() % POKEMON_MAX) + 1
            emit(getPokemon(id))
            delay(1000)
        }
    }

    private suspend fun getPokemon(id: Int): PokemonData {
        val pokeDoc = db.collection(POKEMONS_COLLECTION)
            .document(id.toString())
            .get()
            .await()

        return pokeDoc
            .toObject(FirestorePokemonDataDTO::class.java)!!
            .toPokemonData()
    }


    data class FirestorePokemonDataDTO(
        val id: Int = -1,
        val name: String = "",
        val imageUrl: String = "",
        val height: Int = -1, // decimeter
        val weight: Int = -1  // hectograms
    ) {
        companion object {
            val None = PokemonData(-1, "none", "", -1, -2)
        }

        fun toPokemonData(): PokemonData {
            return PokemonData(
                id, name, imageUrl, height, weight
            )
        }
    }
}