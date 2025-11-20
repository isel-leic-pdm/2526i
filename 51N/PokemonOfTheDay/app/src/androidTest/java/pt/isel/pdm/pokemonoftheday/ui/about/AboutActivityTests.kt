package pt.isel.pdm.pokemonoftheday.ui.about

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pt.isel.pdm.pokemonoftheday.DemoPokemonTestApplication

class AboutActivityTests {
    @get:Rule
    var activityRule = createAndroidComposeRule<AboutActivity>()

    @Test
    fun `About activity correctly shows favPokemon`() {


        activityRule.onNodeWithTag("favPokemon")
            .assertDoesNotExist()

        val app = ApplicationProvider.getApplicationContext<DemoPokemonTestApplication>()

        runBlocking {
            app.pokemonFavouriteService.set(123)
        }

        activityRule.waitUntil(timeoutMillis = 5_000) {
            activityRule.onAllNodesWithTag("favPokemon")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        activityRule.onNodeWithTag("favPokemon")
            .assertExists()
    }
}