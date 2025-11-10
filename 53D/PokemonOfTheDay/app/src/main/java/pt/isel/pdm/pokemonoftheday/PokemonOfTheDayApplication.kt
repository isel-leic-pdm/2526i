package pt.isel.pdm.pokemonoftheday

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import pt.isel.pdm.pokemonoftheday.services.DataStorePokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.services.FirestorePokedexService
import pt.isel.pdm.pokemonoftheday.services.FirestoreTestService
import pt.isel.pdm.pokemonoftheday.services.PokedexService
import pt.isel.pdm.pokemonoftheday.services.PokemonFavouriteService

interface DependencyContainer {
    val pokedexService: PokedexService
    val favouriteService: PokemonFavouriteService
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
                FirestorePokedexService(firestoreDatabase)
            }

    override val favouriteService: PokemonFavouriteService
            by lazy {
                //FakePokemonFavouriteService()
                DataStorePokemonFavouriteService(appDataStore)
            }

    val firestorePlaygroundService by lazy {
        FirestoreTestService(firestoreDatabase)
    }

    override fun onCreate() {
        Log.d("App", "App initializing...")
        super.onCreate()
    }
}