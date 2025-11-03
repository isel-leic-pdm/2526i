package pt.isel.pdm.pokemonoftheday

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.services.PokedexService
import pt.isel.pdm.pokemonoftheday.services.PokemonFavouriteService

interface DependencyContainer {
    val pokedexService: PokedexService
    val favouriteService: PokemonFavouriteService
}

class PokemonOfTheDayApplication : Application(),
    DependencyContainer {

    val appDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override val pokedexService: PokedexService
            by lazy { FakePokedexService() }

    override val favouriteService: PokemonFavouriteService
            by lazy { FakePokemonFavouriteService() }

    override fun onCreate() {
        Log.d("App", "App initializing...")
        super.onCreate()
    }
}