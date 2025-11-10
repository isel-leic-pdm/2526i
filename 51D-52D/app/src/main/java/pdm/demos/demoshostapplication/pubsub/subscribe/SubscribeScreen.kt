package pdm.demos.demoshostapplication.pubsub.subscribe

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.demoshostapplication.R
import pdm.demos.demoshostapplication.pubsub.common.FakeMessageBoardService
import pdm.demos.demoshostapplication.pubsub.common.MessageBoard
import pdm.demos.demoshostapplication.ui.TopBar
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

@Composable
fun SubscribeScreen(viewModel: SubscribeScreenViewModel, onBackNavigationIntent: () -> Unit) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.pub_sub_title),
                onBackIntent = onBackNavigationIntent,
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        val messageBoards by viewModel.messageBoards.collectAsState(initial = emptyList())
        LazyColumn(modifier = Modifier.padding(innerPadding).padding(all = 24.dp)) {
            items(items = messageBoards) {
                MessageBoardView(board = it)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun MessageBoardView(board: MessageBoard) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = board.boardId,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        )
        Text(
            text = board.message ?: "",
            modifier = Modifier.padding(all = 16.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SubscribeScreenPreview() {
    DemosHostApplicationTheme {
        SubscribeScreen(
            viewModel = SubscribeScreenViewModel(service = FakeMessageBoardService()),
            onBackNavigationIntent = { }
        )
    }
}