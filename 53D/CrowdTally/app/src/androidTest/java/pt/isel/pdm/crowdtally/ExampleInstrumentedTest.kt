package pt.isel.pdm.crowdtally

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import pt.isel.pdm.crowdtally.ui.main.CrowCounterConfigurator
import pt.isel.pdm.crowdtally.ui.main.CrowdTallyContent
import pt.isel.pdm.crowdtally.ui.main.TestTagConfigurationButton
import pt.isel.pdm.crowdtally.ui.main.TestTagCounterText
import pt.isel.pdm.crowdtally.ui.main.TestTagIncrementButton
import pt.isel.pdm.crowdtally.ui.main.TestTagMaxCrowdInput

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var composeRule = createComposeRule()

    @Test
    fun `counter is displayed correctly`() {
        composeRule.setContent {
            CrowdTallyContent(
                crowdCounter = 123,
                onDecrement = {},
                onIncrement = {},
                incrementEnabled = true,
                decrementEnabled = true
            )
        }

        composeRule
            .onNodeWithTag(TestTagCounterText)
            .assertExists()
            .assertTextEquals(123.toString())
    }

    @Test
    fun `counter increment is called when button is pushed`() {


        var wasClicked = false
        composeRule.setContent {
            CrowdTallyContent(
                crowdCounter = 123,
                onDecrement = {},
                onIncrement = {
                    wasClicked = true
                },
                incrementEnabled = true,
                decrementEnabled = true
            )
        }

        composeRule
            .onNodeWithTag(TestTagIncrementButton)
            .assertExists()
            .performClick()

        assertEquals(true, wasClicked)
    }


    @Test
    fun `configuration is dealing with text input correctly`() {
        var max = -1
        composeRule.setContent {
            CrowCounterConfigurator(
                1,
                { newMax ->
                    max = newMax
                })
        }

        composeRule.onNodeWithTag(TestTagMaxCrowdInput)
            .performTextReplacement("123")

        composeRule.onNodeWithTag(TestTagConfigurationButton)
            .performClick()

        assertEquals(123, max)
    }
}