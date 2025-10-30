package pdm.demos.demoshostapplication.joke.reactive

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.demoshostapplication.R
import pdm.demos.demoshostapplication.joke.common.domain.FakeJokesService
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

@Composable
fun JokeOfDayScreen(viewModel: JokeOfDayScreenViewModel, onBackIntent: () -> Unit) {

    val currentState by viewModel.currentState.collectAsState(JokeOfDayScreenState.Idle)

    when (currentState) {
        is JokeOfDayScreenState.Idle -> {
            JokeScreenView(
                joke = null,
                onBackNavigate = onBackIntent
            )
        }

        is JokeOfDayScreenState.Success -> {
            val joke = (currentState as JokeOfDayScreenState.Success).joke
            JokeScreenView(
                joke = joke,
                onBackNavigate = onBackIntent
            )
        }

        is JokeOfDayScreenState.Error -> {
            val errorState = currentState as JokeOfDayScreenState.Error
            JokeScreenView(
                joke = errorState.lastJoke,
                onBackNavigate = onBackIntent,
                error = stringResource(id = R.string.jokes_error_api_message)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JokeOfDayScreenPreview() {
    DemosHostApplicationTheme {
        JokeOfDayScreen(
            viewModel = JokeOfDayScreenViewModel(jokeService = FakeJokesService()),
            onBackIntent = { }
        )
    }
}