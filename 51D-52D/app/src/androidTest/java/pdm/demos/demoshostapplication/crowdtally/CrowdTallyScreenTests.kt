package pdm.demos.demoshostapplication.crowdtally

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import org.junit.Rule
import org.junit.Test
import pdm.demos.demoshostapplication.ui.CONFIG_BUTTON_TAG

/**
 * Instrumented tests for the [CrowdTallyScreen] composable.
 *
 * DESIGN NOTE:
 * These tests focus on verifying the state transitions of the screen based on user interactions.
 * Each state is represented by a different view, so the tests check for the presence of specific
 * UI elements that are unique to each state.
 * The individual views (CountingView and ConfigurationView) have their own dedicated tests.
 */
class CrowdTallyScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun initially_the_screen_is_at_the_counting_state() {
        composeTestRule.setContent {
            CrowdTallyScreen(
                viewModel = CrowdTallyViewModel(),
                onBackNavigationIntent = {}
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = COUNTING_VIEW_TAG)
            .assertExists()
    }

    @Test
    fun clicking_configuration_icon_transitions_to_the_configuration_state() {
        composeTestRule.setContent {
            CrowdTallyScreen(
                viewModel = CrowdTallyViewModel(),
                onBackNavigationIntent = {}
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = CONFIG_BUTTON_TAG)
            .performClick()

        composeTestRule
            .onNodeWithTag(testTag = CONFIGURE_VIEW_TAG)
            .assertExists()
    }

    @Test
    fun saving_configuration_transitions_back_to_the_counting_state() {
        composeTestRule.setContent {
            CrowdTallyScreen(
                viewModel = CrowdTallyViewModel(),
                onBackNavigationIntent = {}
            )
        }

        // Transition to the configuration state
        composeTestRule
            .onNodeWithTag(testTag = CONFIG_BUTTON_TAG)
            .performClick()

        // Change configuration and save it. We need to change it because the onSaveIntent is
        // not called if the user has not changed the configuration
        val newCapacity = 10
        composeTestRule
            .onNodeWithTag(testTag = CAPACITY_TEXT_FIELD_TAG)
            .performTextReplacement(text = newCapacity.toString())
        composeTestRule
            .onNodeWithTag(testTag = SAVE_BUTTON_TAG)
            .performClick()

        // Expecting to be back at the counting state
        composeTestRule
            .onNodeWithTag(testTag = COUNTING_VIEW_TAG)
            .assertExists()
    }

    @Test
    fun cancelling_configuration_transitions_back_to_the_counting_state() {
        composeTestRule.setContent {
            CrowdTallyScreen(
                viewModel = CrowdTallyViewModel(),
                onBackNavigationIntent = {}
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = CONFIG_BUTTON_TAG)
            .performClick()

        composeTestRule
            .onNodeWithTag(testTag = CANCEL_BUTTON_TAG)
            .performClick()

        composeTestRule
            .onNodeWithTag(testTag = COUNTING_VIEW_TAG)
            .assertExists()
    }
}