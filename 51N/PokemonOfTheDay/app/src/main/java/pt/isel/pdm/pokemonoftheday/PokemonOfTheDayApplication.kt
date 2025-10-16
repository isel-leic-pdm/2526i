package pt.isel.pdm.pokemonoftheday

import android.app.Application
import android.util.Log
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.services.PokedexService

interface DependencyContainer {
    val pokedexService: PokedexService
}


class PokemonOfTheDayApplication : Application(), DependencyContainer {
    override fun onCreate() {
        super.onCreate()
        Log.d("Application", "onCreate")
    }

    override val pokedexService: PokedexService by lazy {
        FakePokedexService()
    }
}