package pdm.demos.demoshostapplication.joke

import androidx.compose.runtime.Composable
import pdm.demos.demoshostapplication.R
import pdm.demos.demoshostapplication.ui.ErrorAlert

@Composable
fun JokeOfDayScreen(viewModel: JokeOfDayScreenViewModel, onBackIntent: () -> Unit) {

    val currentState: JokeOfDayScreenState = viewModel.currentState

    when (currentState) {
        is JokeOfDayScreenState.Idle -> IdleView(
            onFetchRequested = { viewModel.fetchJoke() },
            onBackRequested = onBackIntent
        )

        is JokeOfDayScreenState.Loading -> LoadingView()

        is JokeOfDayScreenState.Success -> SuccessView(
            joke = currentState.joke,
            onFetchRequested = { viewModel.fetchJoke() },
            onBackRequested = onBackIntent
        )

        is JokeOfDayScreenState.Error -> ErrorAlert(
            title = R.string.jokes_error_api_title,
            message = R.string.jokes_error_could_not_reach_api,
            buttonText = R.string.jokes_error_retry_button_text,
            onDismiss = { viewModel.resetToIdle() }
        )
    }

}