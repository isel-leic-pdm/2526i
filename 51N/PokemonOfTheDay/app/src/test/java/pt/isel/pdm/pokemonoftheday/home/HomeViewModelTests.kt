package pt.isel.pdm.pokemonoftheday.home

import androidx.lifecycle.SavedStateHandle
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import pt.isel.pdm.pokemonoftheday.AppBaseTest
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteHistoryService
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.ui.home.HomeViewModel
import pt.isel.pdm.pokemonoftheday.ui.home.HomeViewState

class HomeViewModelTests : AppBaseTest() {

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var favHistoryPokiService: FakePokemonFavouriteHistoryService
    private lateinit var favPokiService: FakePokemonFavouriteService
    private lateinit var pokedexService: FakePokedexService
    private lateinit var viewModel: HomeViewModel

    @Before
    fun createTestServices() {
        pokedexService = FakePokedexService()
        favPokiService = FakePokemonFavouriteService()
        favHistoryPokiService = FakePokemonFavouriteHistoryService()
        savedStateHandle = SavedStateHandle()

        viewModel =
            HomeViewModel(savedStateHandle, pokedexService, favPokiService, favHistoryPokiService)

    }

    @Test
    fun `HomeViewModel starts on empty state `() = runTest {
        assertTrue(viewModel.screenState is HomeViewState.Empty)
    }

    @Test
    fun `HomeViewModel shows valid pokemon after a refresh `() = runTest {

        viewModel.refreshPokemonOfTheDay()
        advanceUntilIdle()

        assertTrue(viewModel.screenState is HomeViewState.ValidPokemon)

    }

    @Test
    fun `HomeViewModel sets current favourite correctly `() = runTest {

        viewModel.refreshPokemonOfTheDay()
        advanceUntilIdle()
        assertTrue(viewModel.screenState is HomeViewState.ValidPokemon)

        val pokemon = (viewModel.screenState as HomeViewState.ValidPokemon).pokemon
        viewModel.setCurrentAsFavourite()
        advanceUntilIdle()

        assertEquals(pokemon.id, favPokiService.currentFavourite.first())
    }

}