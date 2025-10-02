package pdm.demos.demoshostapplication.crowdtally

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import pdm.demos.demoshostapplication.crowdtally.domain.CrowdCounter
import pdm.demos.demoshostapplication.crowdtally.domain.CrowdTallyScreenState.Counting

class CountingViewTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun counter_value_is_correctly_displayed() {
        val expectedValue = 10
        composeTestRule.setContent {
            CountingView(
                state = Counting(counter = CrowdCounter(value = expectedValue))
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = COUNTER_VALUE_TAG, useUnmergedTree = true)
            .assertTextEquals(expectedValue.toString())
    }

    @Test
    fun clicking_plus_button_increases_count() {
        val initialValue = 10
        val expectedValue = initialValue + 1
        composeTestRule.setContent {
            CountingView(
                state = Counting(counter = CrowdCounter(value = initialValue))
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = PLUS_BUTTON_TAG)
            .performClick()

        composeTestRule
            .onNodeWithTag(testTag = COUNTER_VALUE_TAG, useUnmergedTree = true)
            .assertTextEquals(expectedValue.toString())
    }

    @Test
    fun clicking_minus_button_decreases_count() {
        val initialValue = 10
        val expectedValue = initialValue - 1
        composeTestRule.setContent {
            CountingView(
                state = Counting(counter = CrowdCounter(value = initialValue))
            )
        }

        composeTestRule
            .onNodeWithTag(testTag = MINUS_BUTTON_TAG)
            .performClick()

        composeTestRule
            .onNodeWithTag(testTag = COUNTER_VALUE_TAG, useUnmergedTree = true)
            .assertTextEquals(expectedValue.toString())
    }

    @Test
    fun minus_button_is_disabled_when_at_minimum() {
        val initialValue = 0
        composeTestRule.setContent {
            CountingView(
                state = Counting(counter = CrowdCounter(value = initialValue))
            )
        }

        composeTestRule.onNodeWithTag(testTag = MINUS_BUTTON_TAG).assertIsNotEnabled()
        composeTestRule.onNodeWithTag(testTag = PLUS_BUTTON_TAG).assertIsEnabled()
    }

    @Test
    fun plus_button_is_disabled_when_at_maximum() {
        val value = 500
        composeTestRule.setContent {
            CountingView(
                state = Counting(counter = CrowdCounter(value = value, capacity = value))
            )
        }

        composeTestRule.onNodeWithTag(testTag = MINUS_BUTTON_TAG).assertIsEnabled()
        composeTestRule.onNodeWithTag(testTag = PLUS_BUTTON_TAG).assertIsNotEnabled()
    }
}