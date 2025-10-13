package pdm.demos.demoshostapplication.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import pdm.demos.demoshostapplication.R
import pdm.demos.demoshostapplication.ui.TopBar
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

/**
 * Represents the possible navigation destinations of the main screen.
 *
 * DESIGN NOTE:
 * Each screen's composable, instead of dealing itself with the navigation details, generates an
 * event expressing the user intention of navigating to another screen. This approach simplifies
 * automated testing. This approach will be used in ALL screens, regardless of how navigation is
 * actually made.
 */
enum class TitleScreenNavigationIntent {
    NavigateToAbout,
    NavigateToCrowdTally,
    NavigateToUserTask,
    NavigateToJokeOfTheDay
}

const val CROWD_TALLY_BUTTON_TAG = "crowd_tally_button"
const val USER_TASK_BUTTON_TAG = "user_task_button"
const val JOKE_OF_DAY_BUTTON_TAG = "joke_button"

/**
 * The composable that corresponds to the main screen UX. Navigation to other screens is not
 * addressed herein. Instead, an event is produced to signal the user's intentions.
 */
@Composable
fun MainScreen(
        modifier: Modifier = Modifier,
        onNavigate: (TitleScreenNavigationIntent) -> Unit = { }
    ) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.main_title),
                onInfoIntent = { onNavigate(TitleScreenNavigationIntent.NavigateToAbout) },
            )
         },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.fillMaxSize().padding(paddingValues = innerPadding)
        ) {
            Button(
                onClick = { onNavigate(TitleScreenNavigationIntent.NavigateToCrowdTally) },
                modifier = Modifier.testTag(tag = CROWD_TALLY_BUTTON_TAG)
            ) {
                Text(text = stringResource(id = R.string.main_navigate_crowd_tally) )
            }
            Button(
                onClick = { onNavigate(TitleScreenNavigationIntent.NavigateToUserTask) },
                modifier = Modifier.testTag(tag = USER_TASK_BUTTON_TAG)
            ) {
                Text(text = stringResource(id = R.string.main_navigate_user_task) )
            }
            Button(
                onClick = { onNavigate(TitleScreenNavigationIntent.NavigateToJokeOfTheDay) },
                modifier = Modifier.testTag(tag = JOKE_OF_DAY_BUTTON_TAG)
            ) {
                Text(text = stringResource(id = R.string.main_navigate_jokes) )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun MainScreenPreview() {
    DemosHostApplicationTheme {
        MainScreen(
            modifier = Modifier.padding(all = 50.dp)
        )
    }
}
