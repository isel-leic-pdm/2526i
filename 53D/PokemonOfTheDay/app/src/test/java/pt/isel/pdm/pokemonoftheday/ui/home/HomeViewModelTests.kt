package pt.isel.pdm.pokemonoftheday.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
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
    fun `HomeViewModel starts on empty state `() {
        //  given | arrange
        val vm = viewModel
        //  when | act
        ;
        //  then | assert
        assertEquals(HomeViewState.None, vm.state)
    }

    @Test
    fun `HomeViewModel shows valid pokemon after a refresh `() = runTest {
        //  given | arrange
        val vm = viewModel
        //  when | act
        vm.refreshPokemonOfTheDay()
        advanceUntilIdle()
        //  then | assert
        assertTrue(vm.state is HomeViewState.Content)
    }

    @Test
    fun `HomeViewModel shows error state when PokedexService fails to get one `() = runTest {
        //  given | arrange
        val vm = viewModel
        pokedexService.setErrorOnAllRequests()

        //  when | act
        vm.refreshPokemonOfTheDay()
        advanceUntilIdle()

        //  then | assert
        assertTrue(vm.state is HomeViewState.Error)
    }

    @Test
    fun `HomeViewModel sets current favourite correctly `() = runTest {
        //  given | arrange
        val vm = viewModel

        //  when | act
        vm.refreshPokemonOfTheDay()
        advanceUntilIdle()

        assertTrue(vm.state is HomeViewState.Content)

        val pokeId = (vm.state as HomeViewState.Content).pokemon.id
        vm.toggleFavourite()
        advanceUntilIdle()

        //  then | assert
        //tests that vm changed state when fav was pressed
        assertTrue((vm.state as HomeViewState.Content).isFav)
        //tests that vm called the service to store the fav
        assertEquals(pokeId, favPokiService.favourite.first())

    }

}