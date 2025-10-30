package pt.isel.pdm.pokemonoftheday

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import pt.isel.pdm.pokemonoftheday.services.DataStoreStorageService
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.services.FakeStorageService
import pt.isel.pdm.pokemonoftheday.services.PokedexService
import pt.isel.pdm.pokemonoftheday.services.StorageService

interface DependencyContainer {
    val pokedexService: PokedexService
    val storageService: StorageService
}

class PokemonOfTheDayApplication : Application(),
    DependencyContainer {

    val appDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override val pokedexService: PokedexService
            by lazy { FakePokedexService() }
    override val storageService: StorageService
            by lazy {
                //FakeStorageService()
                  DataStoreStorageService(appDataStore)
            }

    override fun onCreate() {
        Log.d("App", "App initializing...")
        super.onCreate()
    }
}