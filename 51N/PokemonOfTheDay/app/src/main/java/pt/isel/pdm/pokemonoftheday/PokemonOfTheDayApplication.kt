package pt.isel.pdm.pokemonoftheday

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import pt.isel.pdm.pokemonoftheday.domain.database.AppDatabase
import pt.isel.pdm.pokemonoftheday.services.DataStorePokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.services.FirestorePokedexService
import pt.isel.pdm.pokemonoftheday.services.FirestoreTestService
import pt.isel.pdm.pokemonoftheday.services.PokeApiPokedexService
import pt.isel.pdm.pokemonoftheday.services.PokedexService
import pt.isel.pdm.pokemonoftheday.services.PokemonFavouriteHistoryService
import pt.isel.pdm.pokemonoftheday.services.PokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.services.RoomPokemonFavouriteHistoryService
import java.util.logging.Logger
import kotlin.getValue

interface DependencyContainer {
    val pokedexService: PokedexService
    val pokemonFavouriteService: PokemonFavouriteService
    val pokemonFavouriteHistoryService: PokemonFavouriteHistoryService
}


class PokemonOfTheDayApplication : Application(), DependencyContainer {
    val appDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val firebaseFirestore by lazy {
        Firebase.firestore

    }

    val httpClient by lazy {
        HttpClient {
            install(plugin = ContentNegotiation) { // handles serialization/deserialization
                json(
                    json = Json {
                        prettyPrint = true //written json is idented
                        isLenient = true // more easy on the "correctness" of the files
                        ignoreUnknownKeys = true //ignore fields not in your data class
                    }
                )
            }
            install(Logging) {
                level = LogLevel.ALL
                //logger = io.ktor.client.plugins.logging.Logger.ANDROID
                logger = object : io.ktor.client.plugins.logging.Logger {
                    override fun log(message: String) {
                        //Log.d("KtorClient", message)
                    }

                }
            }
        }
    }


    val appDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "appdb23"
        )
            .fallbackToDestructiveMigration() //DEV ONLY, DELETES DB
            .build()
    }

    val firestorePlaygroundService by lazy {
        FirestoreTestService(firebaseFirestore)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("Application", "onCreate")
    }

    override val pokedexService: PokedexService by lazy {
        //FakePokedexService()
        //FirestorePokedexService(firebaseFirestore)
        PokeApiPokedexService(httpClient)
    }
    override val pokemonFavouriteService: PokemonFavouriteService by lazy {
        //FakePokemonFavouriteService()
        DataStorePokemonFavouriteService(appDataStore)
    }

    override val pokemonFavouriteHistoryService: PokemonFavouriteHistoryService by lazy {
        RoomPokemonFavouriteHistoryService(appDatabase.pokemonHistoryDao())
    }
}