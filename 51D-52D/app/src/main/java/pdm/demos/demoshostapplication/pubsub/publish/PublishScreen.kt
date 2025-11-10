package pdm.demos.demoshostapplication.pubsub.publish

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.demoshostapplication.R
import pdm.demos.demoshostapplication.pubsub.common.FakeMessageBoardService
import pdm.demos.demoshostapplication.ui.TopBar

@Composable
fun PublishScreen(viewModel: PublishScreenViewModel, onBackNavigationIntent: () -> Unit) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.pub_sub_title),
                onBackIntent = onBackNavigationIntent,
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->

        val currState by viewModel.screenState.collectAsState()
        val maybeError =
            if (currState is PublishScreenState.PublishError)
                stringResource(R.string.pub_sub_publish_error)
            else null

        PublishForm(
            loading = currState is PublishScreenState.Publishing,
            error = maybeError,
            onPublish = { messageBoard -> viewModel.publishMessage(messageBoard) },
            validateData = { message, board -> message.isNotBlank() && board.isNotBlank() },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
fun PublishScreenPreview() {
    PublishScreen(
        viewModel = PublishScreenViewModel(service = FakeMessageBoardService()),
        onBackNavigationIntent = { }
    )
}