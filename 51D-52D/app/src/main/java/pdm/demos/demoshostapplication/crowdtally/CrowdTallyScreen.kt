package pdm.demos.demoshostapplication.crowdtally

import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.demoshostapplication.R
import pdm.demos.demoshostapplication.ui.TopBar
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

/**
 * Composable that corresponds to the main screen of the crowd tally demo.
 * @param onBackNavigationIntent The callback to be invoked when the user intends to navigate back.
 * @param viewModel The [CrowdTallyViewModel] that holds the screen state.
 * @param modifier The modifier to be applied to this composable.
 */
@Composable
fun CrowdTallyScreen(
    onBackNavigationIntent: () -> Unit,
    viewModel: CrowdTallyViewModel,
    modifier: Modifier = Modifier
) {
    val observedState = viewModel.currentState

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.crowd_tally_title),
                onBackIntent = onBackNavigationIntent,
                onConfigurationIntent = { viewModel.configure() }
            )
        },
        modifier = modifier.imePadding()
    ) { innerPadding ->

        when (observedState) {
            is CrowdTallyScreenState.Counting ->
                CountingView(
                    state = observedState,
                    modifier = Modifier.padding(paddingValues = innerPadding)
                )

            is CrowdTallyScreenState.Configuration ->
                ConfigurationView(
                    state = observedState,
                    onSaveIntent = { viewModel.saveConfiguration(newCapacity = it) },
                    onCancelIntent = { viewModel.cancelConfiguration() },
                )
        }
    }
}

@Preview
@Composable
fun CrowdTallyScreenPreview() {
    DemosHostApplicationTheme {
        CrowdTallyScreen(
            viewModel = CrowdTallyViewModel(),
            onBackNavigationIntent = { }
        )
    }
}