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
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.services.FirestorePokedexService
import pt.isel.pdm.pokemonoftheday.services.FirestoreTestService
import pt.isel.pdm.pokemonoftheday.services.PokeApiPokedexService
import pt.isel.pdm.pokemonoftheday.services.PokedexService
import pt.isel.pdm.pokemonoftheday.services.PokemonFavouriteHistoryService
import pt.isel.pdm.pokemonoftheday.services.PokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.services.RoomPokemonFavouriteHistoryService

interface DependencyContainer {
    val pokedexService: PokedexService
    val favouriteService: PokemonFavouriteService
    val favouriteHistoryService : PokemonFavouriteHistoryService
}

class PokemonOfTheDayApplication : Application(),
    DependencyContainer {

    val appDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val firestoreDatabase by lazy {
        Firebase.firestore
    }
    override val pokedexService: PokedexService
            by lazy {
                //FakePokedexService()
                //FirestorePokedexService(firestoreDatabase)
                PokeApiPokedexService(httpClient)
            }

    override val favouriteService: PokemonFavouriteService
            by lazy {
                //FakePokemonFavouriteService()
                DataStorePokemonFavouriteService(appDataStore)
            }

    override val favouriteHistoryService: PokemonFavouriteHistoryService
            by lazy {
                RoomPokemonFavouriteHistoryService(appDatabase.favPokemonsHistoryDao())
            }

    val firestorePlaygroundService by lazy {
        FirestoreTestService(firestoreDatabase)
    }

    val httpClient by lazy {
        HttpClient() {
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
                        Log.d("KtorClient", message)
                    }

                }
            }
        }
    }


    val appDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "appdb"
        )
            .fallbackToDestructiveMigration() //DEV ONLY, DELETES DB
            .build()
    }

    override fun onCreate() {
        Log.d("App", "App initializing...")
        super.onCreate()
    }
}