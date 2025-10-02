package pt.isel.pdm.crowdtally

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import pt.isel.pdm.crowdtally.ui.main.CROWDTALLYCONFIGURATORCONTENT_TEXTFIELD
import pt.isel.pdm.crowdtally.ui.main.CROWDTALLYCONTENT_INC_BUTTON
import pt.isel.pdm.crowdtally.ui.main.CrowdTallyConfiguratorContent
import pt.isel.pdm.crowdtally.ui.main.CrowdTallyContent
import kotlin.math.max

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("pt.isel.pdm.crowdtally", appContext.packageName)
    }

    @Test
    fun `CrowdTallyContent - button click increments counter`() {
        var counter = 99
        composeTestRule.setContent {
            CrowdTallyContent(
                counter,
                101,
                { counter++ },
                {},
                true,
                true
            )
        }

        composeTestRule
            .onNodeWithTag(CROWDTALLYCONTENT_INC_BUTTON)
            .assertExists()
            .performClick()



        assertEquals(100, counter)
    }


    @Test
    fun `CrowdTallyConfigurator - changes max value`() {

        var maxCrowd = 5

        composeTestRule.setContent {
            CrowdTallyConfiguratorContent(
                maxCrowd,
                {
                    maxCrowd = it
                })
        }

        composeTestRule
            .onNodeWithText("$maxCrowd")
            .assertExists()

        composeTestRule.onNodeWithTag(CROWDTALLYCONFIGURATORCONTENT_TEXTFIELD)
            .assertExists()
            .performTextReplacement("123")

        assertEquals(123, maxCrowd)


    }
}