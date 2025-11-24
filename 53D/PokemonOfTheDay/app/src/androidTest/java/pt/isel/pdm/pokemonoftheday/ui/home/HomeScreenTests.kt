package pt.isel.pdm.pokemonoftheday.ui.home


import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.SavedStateHandle
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteHistoryService
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.ui.common.CustomAppTopBarTestTags
import pt.isel.pdm.pokemonoftheday.ui.common.PokemonViewsTestTags

@RunWith(AndroidJUnit4::class)
class HomeScreenTests {


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

    @get:Rule
    val composeRule = createComposeRule()

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
    fun `HomeScreen shows ViewModel Pokemon`() {

        var buttonPressed = false
        val vm = viewModel


        composeRule.setContent {
            HomeScreen(
                {},
                {},
                {},
                vm
            )
        }

        vm.refreshPokemonOfTheDay()

        composeRule.onNodeWithTag(PokemonViewsTestTags.FSPD_ID_TAG)
            .assertExists()
            .assertTextEquals((vm.state as HomeViewState.Content).pokemon.id.toString())


    }
}
