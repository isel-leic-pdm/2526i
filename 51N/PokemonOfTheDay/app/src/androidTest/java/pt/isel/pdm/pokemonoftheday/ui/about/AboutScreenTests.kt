package pt.isel.pdm.pokemonoftheday.ui.about

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.ui.common.CustomAppTopBarTestTags
import pt.isel.pdm.pokemonoftheday.ui.common.CustomAppTopBarTests

@RunWith(AndroidJUnit4::class)
class AboutScreenTests {


    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `AboutScreen back is called when pushed`() {

        var buttonPressed = false
        composeRule.setContent {
            AboutScreen(
                AboutViewModel(FakePokemonFavouriteService()),
                {
                    buttonPressed = true
                })
        }

        composeRule.onNodeWithTag(CustomAppTopBarTestTags.BACK_BUTTON)
            .assertExists()
            .performClick()


        assert(buttonPressed)
    }
}