package pt.isel.pdm.pokemonoftheday

import android.app.Application
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import pt.isel.pdm.pokemonoftheday.services.DataStorePokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.services.PokedexService
import pt.isel.pdm.pokemonoftheday.services.PokemonFavouriteService

interface DependencyContainer {
    val pokedexService: PokedexService
    val pokemonFavouriteService: PokemonFavouriteService
}


class PokemonOfTheDayApplication : Application(), DependencyContainer {
    val appDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate() {
        super.onCreate()
        Log.d("Application", "onCreate")
    }

    override val pokedexService: PokedexService by lazy {
        FakePokedexService()
    }
    override val pokemonFavouriteService: PokemonFavouriteService by lazy {
        //FakePokemonFavouriteService()
        DataStorePokemonFavouriteService(appDataStore)
    }
}