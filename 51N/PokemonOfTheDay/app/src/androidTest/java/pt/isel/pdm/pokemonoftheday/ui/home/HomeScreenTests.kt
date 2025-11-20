package pt.isel.pdm.pokemonoftheday.ui.home


import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.SavedStateHandle
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import okhttp3.internal.wait
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pt.isel.pdm.pokemonoftheday.ReplaceMainDispatcherRule
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteHistoryService
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.ui.common.CustomAppTopBarTestTags
import pt.isel.pdm.pokemonoftheday.ui.common.PokemonViewsTestTags
import pt.isel.pdm.pokemonoftheday.ui.firestorePlayground.test

@RunWith(AndroidJUnit4::class)
class HomeScreenTests  {



    @get:Rule
    val composeRule = createComposeRule()

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
    fun `HomeScreen about is pressed when clicked`() {

        var buttonPressed = false
        composeRule.setContent {
            HomeScreen(
                {
                    buttonPressed = true
                },
                {},
                {},
                viewModel
            )
        }

        composeRule.onNodeWithTag(CustomAppTopBarTestTags.ABOUT_BUTTON)
            .assertExists()
            .performClick()


        assert(buttonPressed)
    }

    @Test
    fun `HomeScreen shows ViewModel Pokemon`()  = runTest{
        val vm = viewModel

        composeRule.setContent {
            HomeScreen(
                {},
                { },
                { },
                vm
            )
        }

        vm.refreshPokemonOfTheDay()

        composeRule.waitUntil(5_000) {
            vm.screenState is HomeViewState.ValidPokemon
        }

        val state = vm.screenState as HomeViewState.ValidPokemon;

        assertNotNull(state)


        composeRule.waitUntil {
            composeRule.onAllNodesWithTag(PokemonViewsTestTags.FSPD_ID_TAG).fetchSemanticsNodes().isNotEmpty()
        }

    }
}
