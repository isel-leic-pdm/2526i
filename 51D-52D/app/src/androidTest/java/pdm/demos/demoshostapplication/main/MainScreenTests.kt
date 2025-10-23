package pdm.demos.demoshostapplication.main

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import pdm.demos.demoshostapplication.ui.INFO_BUTTON_TAG

class MainScreenTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clicking_InfoButton_triggersNavigateToAboutIntent() {
        var navigationIntent: TitleScreenNavigationIntent? = null
        composeTestRule.setContent {
            MainScreen(
                onNavigate = { navigationIntent = it }
            )
        }

        composeTestRule.onNodeWithTag(testTag = INFO_BUTTON_TAG).performClick()
        assert(navigationIntent == TitleScreenNavigationIntent.NavigateToAbout)
    }

    @Test
    fun clicking_CrowdTallyButton_triggersNavigateToCrowdTallyIntent() {
        var navigationIntent: TitleScreenNavigationIntent? = null
        composeTestRule.setContent {
            MainScreen(
                onNavigate = { navigationIntent = it }
            )
        }

        composeTestRule.onNodeWithTag(testTag = CROWD_TALLY_BUTTON_TAG).performClick()
        assert(navigationIntent == TitleScreenNavigationIntent.NavigateToCrowdTally)
    }

    @Test
    fun clicking_UserTaskButton_triggersNavigateToUserTaskIntent() {
        var navigationIntent: TitleScreenNavigationIntent? = null
        composeTestRule.setContent {
            MainScreen(
                onNavigate = { navigationIntent = it }
            )
        }

        composeTestRule.onNodeWithTag(testTag = USER_TASK_BUTTON_TAG).performClick()
        assert(navigationIntent == TitleScreenNavigationIntent.NavigateToUserTask)
    }

    @Test
    fun clicking_JokeOfDayButton_triggersNavigateToJokeOfTheDayIntent() {
        var navigationIntent: TitleScreenNavigationIntent? = null
        composeTestRule.setContent {
            MainScreen(
                onNavigate = { navigationIntent = it }
            )
        }
        composeTestRule.onNodeWithTag(testTag = JOKE_OF_DAY_BUTTON_TAG).performClick()
        assert(navigationIntent == TitleScreenNavigationIntent.NavigateToJokeOfTheDay)
    }

    @Test
    fun clicking_LoginButton_triggersNavigateToLoginIntent() {
        var navigationIntent: TitleScreenNavigationIntent? = null
        composeTestRule.setContent {
            MainScreen(
                onNavigate = { navigationIntent = it }
            )
        }
        composeTestRule.onNodeWithTag(testTag = LOGIN_BUTTON_TAG).performClick()
        assert(navigationIntent == TitleScreenNavigationIntent.NavigateToLogin)
    }
}