package pdm.demos.demoshostapplication.usertask

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class UserTaskScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clicking_BackButton_triggersOnBackIntent() {
        composeTestRule.setContent {
        }
    }

}