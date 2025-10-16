package pt.isel.pdm.pokemonoftheday.ui.common

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pt.isel.pdm.pokemonoftheday.R

@RunWith(AndroidJUnit4::class)
class CustomAppTopBarTests {



    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `CustomAppBar always has a title`() {
        composeRule.setContent {
            CustomAppTopBar()
        }


        composeRule.onNodeWithTag(CustomAppTopBarTestTags.TITLE_TEXT)
            .assertExists()
            .assert(hasText("").not())

    }

    @Test
    fun `CustomAppBar sets configured title`() {
        var context = InstrumentationRegistry.getInstrumentation().targetContext

        composeRule.setContent {
            CustomAppTopBar(title = R.string.title_activity_about)
        }

        composeRule.onNodeWithTag(CustomAppTopBarTestTags.TITLE_TEXT)
            .assertExists()
            .assertTextEquals(context.getString(R.string.title_activity_about))

    }

    @Test
    fun `CustomAppBar sets back and calls it`() {

        var backCalled = false

        composeRule.setContent {
            CustomAppTopBar(
                navActions = NavigationActions(
                    onBackAction = { backCalled = true }
                )
            )
        }

        composeRule.onNodeWithTag(CustomAppTopBarTestTags.BACK_BUTTON)
            .assertExists()
            .performClick()

        assert(backCalled)
    }

    @Test
    fun `CustomAppBar sets about and calls it`() {

        var backCalled = false

        composeRule.setContent {
            CustomAppTopBar(
                navActions = NavigationActions(
                    onAboutAction = { backCalled = true }
                )
            )
        }

        composeRule.onNodeWithTag(CustomAppTopBarTestTags.ABOUT_BUTTON)
            .assertExists()
            .performClick()

        assert(backCalled)
    }

    @Test
    fun `CustomAppBar doesnt set any nav actions`() {

        composeRule.setContent {
            CustomAppTopBar(
            )
        }

        composeRule.onNodeWithTag(CustomAppTopBarTestTags.ABOUT_BUTTON)
            .assertDoesNotExist()

        composeRule.onNodeWithTag(CustomAppTopBarTestTags.BACK_BUTTON)
            .assertDoesNotExist()

    }

}