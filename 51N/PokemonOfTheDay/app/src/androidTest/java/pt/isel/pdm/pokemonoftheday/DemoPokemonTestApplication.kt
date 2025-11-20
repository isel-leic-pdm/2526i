package pt.isel.pdm.pokemonoftheday

import android.app.Application
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteHistoryService
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.services.PokedexService
import pt.isel.pdm.pokemonoftheday.services.PokemonFavouriteHistoryService
import pt.isel.pdm.pokemonoftheday.services.PokemonFavouriteService

class DemoPokemonTestApplication : DependencyContainer, Application() {
    override val pokedexService: PokedexService by lazy {
        FakePokedexService()
    }
    override val pokemonFavouriteService: PokemonFavouriteService by lazy {
        FakePokemonFavouriteService()
    }
    override val pokemonFavouriteHistoryService: PokemonFavouriteHistoryService by lazy {
        FakePokemonFavouriteHistoryService()
    }

}