package pdm.demos.demoshostapplication.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pdm.demos.demoshostapplication.about.AboutActivity
import pdm.demos.demoshostapplication.crowdtally.CrowdTallyActivity
import pdm.demos.demoshostapplication.joke.JokeOfDayActivity
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme
import pdm.demos.demoshostapplication.usertask.UserTaskDemoActivity

/**
 * The application's main activity. It's the host of the screen displayed when the application starts.
 * It displays a list of demos that can be launched.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemosHostApplicationTheme {
                MainScreen(onNavigate = { handleNavigation(navigationEvent = it) })
            }
        }
    }

    /**
     * Handles navigation events from the [MainScreen] composable. The details of the navigation
     * to each demo are encapsulated within their respective activities.
     * @param navigationEvent The navigation event to handle.
     */
    private fun handleNavigation(navigationEvent: TitleScreenNavigationIntent) {
        when (navigationEvent) {
            TitleScreenNavigationIntent.NavigateToAbout -> AboutActivity.navigate(ctx = this)
            TitleScreenNavigationIntent.NavigateToCrowdTally -> CrowdTallyActivity.navigate(ctx = this)
            TitleScreenNavigationIntent.NavigateToUserTask -> UserTaskDemoActivity.navigate(ctx = this)
            TitleScreenNavigationIntent.NavigateToJokeOfTheDay -> JokeOfDayActivity.navigate(ctx = this)
        }
    }
}
