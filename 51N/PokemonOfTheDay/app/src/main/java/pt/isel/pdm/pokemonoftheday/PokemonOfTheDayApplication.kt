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
import kotlin.getValue

interface DependencyContainer {
    val pokedexService: PokedexService
    val pokemonFavouriteService: PokemonFavouriteService
}


class PokemonOfTheDayApplication : Application(), DependencyContainer {
    val appDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val firebaseFirestore by lazy {
        Firebase.firestore

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
        FirestorePokedexService(firebaseFirestore)
    }
    override val pokemonFavouriteService: PokemonFavouriteService by lazy {
        //FakePokemonFavouriteService()
        DataStorePokemonFavouriteService(appDataStore)
    }
}