package pt.isel.pdm.pokemonoftheday.ui.common

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import pt.isel.pdm.pokemonoftheday.domain.PokemonData
import pt.isel.pdm.pokemonoftheday.domain.heightInMeter
import pt.isel.pdm.pokemonoftheday.domain.weightInKilogram
import pt.isel.pdm.pokemonoftheday.services.Pokemons

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PokemonViewTests {


    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `FullScreenPokeminDataView shows all pokemon data`() {
        val poke = Pokemons.List.first()

        composeRule.setContent {
            FullScreenPokemonDataView(
                poke
            )
        }

        composeRule.onNodeWithTag(PokemonViewsTestTags.FSPD_VIEW_TAG).assertExists()
        composeRule.onNodeWithTag(PokemonViewsTestTags.FSPD_ID_TAG).assertExists()
        composeRule.onNodeWithTag(PokemonViewsTestTags.FSPD_IMAGE_TAG).assertExists()
        composeRule.onNodeWithTag(PokemonViewsTestTags.FSPD_HEIGHT_TAG).assertExists()
        composeRule.onNodeWithTag(PokemonViewsTestTags.FSPD_WEIGHT_TAG).assertExists()
        composeRule.onNodeWithTag(PokemonViewsTestTags.FSPD_NAME_TAG).assertExists()
    }

    @Test
    fun `FullScreenPokeminDataView shows correct name and id`() {
        val poke = Pokemons.List.first()

        composeRule.setContent {
            FullScreenPokemonDataView(
                poke
            )
        }

        composeRule.onNodeWithTag(PokemonViewsTestTags.FSPD_ID_TAG)
            .assertExists()
            .assertTextEquals(poke.id.toString())

        composeRule.onNodeWithTag(PokemonViewsTestTags.FSPD_NAME_TAG)
            .assertExists()
            .assertTextEquals(poke.name)

    }

    @Test
    fun `FullScreenPokeminDataView shows correct height and weight`() {
        val poke = Pokemons.List.first()

        composeRule.setContent {
            FullScreenPokemonDataView(
                poke
            )
        }




        composeRule.onNodeWithTag(PokemonViewsTestTags.FSPD_HEIGHT_TAG)
            .assertExists()
            .assert(hasText("%.2f".format(poke.heightInMeter()),true,true))

        composeRule.onNodeWithTag(PokemonViewsTestTags.FSPD_WEIGHT_TAG)
            .assertExists()
            .assert(hasText("%.2f".format(poke.weightInKilogram()),true,true))


    }
}