package pdm.demos.demoshostapplication.crowdtally

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import pdm.demos.demoshostapplication.crowdtally.CrowdTallyScreenState.Configuration

class ConfigurationViewTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun initial_capacity_is_correctly_displayed() {
        val initialCapacity = 1000
        composeTestRule.setContent {
            ConfigurationView(
                state = Configuration(capacity = initialCapacity),
                onSaveIntent = {},
                onCancelIntent = {}
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = CAPACITY_TEXT_FIELD_TAG, useUnmergedTree = true)
            .assertTextEquals(initialCapacity.toString())
    }

    @Test
    fun clicking_save_button_calls_onSaveIntent_with_new_capacity() {
        var newCapacity = 0
        val enteredCapacity = 234
        composeTestRule.setContent {
            ConfigurationView(
                state = Configuration(capacity = 10),
                onSaveIntent = { newCapacity = it },
                onCancelIntent = { }
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = CAPACITY_TEXT_FIELD_TAG)
            .performTextReplacement(text = enteredCapacity.toString())
        composeTestRule
            .onNodeWithTag(testTag = SAVE_BUTTON_TAG)
            .performClick()

        assertEquals(enteredCapacity, newCapacity)
    }

    @Test
    fun clicking_cancel_button_calls_onCancelIntent() {
        var onCancelCalled = false
        composeTestRule.setContent {
            ConfigurationView(
                state = Configuration(capacity = 10),
                onSaveIntent = { },
                onCancelIntent = { onCancelCalled = true }
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = CANCEL_BUTTON_TAG)
            .performClick()

        assertTrue(onCancelCalled)
    }

    @Test
    fun initially_save_button_is_disabled_and_cancel_button_is_enabled() {
        composeTestRule.setContent {
            ConfigurationView(
                state = Configuration(capacity = 10),
                onSaveIntent = { },
                onCancelIntent = { }
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = SAVE_BUTTON_TAG)
            .assertIsNotEnabled()

        composeTestRule
            .onNodeWithTag(testTag = CANCEL_BUTTON_TAG)
            .assertIsEnabled()
    }

    @Test
    fun entering_invalid_capacity_disables_save_button() {
        composeTestRule.setContent {
            ConfigurationView(
                state = Configuration(capacity = 10),
                onSaveIntent = { },
                onCancelIntent = { }
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = CAPACITY_TEXT_FIELD_TAG)
            .performTextReplacement(text = "0")
        composeTestRule
            .onNodeWithTag(testTag = SAVE_BUTTON_TAG)
            .assertIsNotEnabled()
    }
}