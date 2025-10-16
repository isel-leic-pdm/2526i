package pt.isel.pdm.pokemonoftheday.ui.home


import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pt.isel.pdm.pokemonoftheday.ui.common.CustomAppTopBarTestTags
import pt.isel.pdm.pokemonoftheday.ui.common.PokemonViewsTestTags

@RunWith(AndroidJUnit4::class)
class HomeScreenTests {


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
                HomeViewModel()
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
        val vm = HomeViewModel()


        composeRule.setContent {
            HomeScreen(
                {},
                vm
            )
        }
		
        vm.refreshPokemonOfTheDay()

        composeRule.onNodeWithTag(PokemonViewsTestTags.FSPD_ID_TAG)
            .assertExists()
            .assertTextEquals(vm.pokemonOfTheDay.id.toString())


    }
}
