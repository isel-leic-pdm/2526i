package pdm.demos.demoshostapplication.usertask

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.demoshostapplication.R
import pdm.demos.demoshostapplication.ui.TopBar
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTaskScreen(
    trace: List<TraceNode>,
    onStartAIntent: (Int) -> Unit,
    onStartBIntent: (Int) -> Unit,
    onBackIntent: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.user_task_title), onBackIntent = onBackIntent)
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .padding(all = 16.dp)
                .fillMaxSize(),
        ) {
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1.0f)) {
                itemsIndexed(items = trace.asReversed()) { index, node ->
                    TraceEntry(
                        index = index,
                        node = node,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            Spacer(Modifier.height(height = 16.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Launch Activity A or B with different flags:")
                Spacer(modifier = Modifier.height(height = 8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(space = 8.dp)) {
                    Button(onClick = { onStartAIntent(0) }) { Text(text = "A Standard") }
                    Button(onClick = { onStartAIntent(Intent.FLAG_ACTIVITY_SINGLE_TOP) }) {
                        Text(text = "A Single Top")
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(space = 8.dp)) {
                    Button(
                        onClick = {
                            onStartAIntent(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        }
                    ) {
                        Text(text = "A Clear Top")
                    }

                    Button(onClick = { onStartAIntent(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT) }) {
                        Text(text = "A Reorder")
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(space = 8.dp)) {
                    Button(onClick = { onStartBIntent(0) }) { Text(text = "B Standard") }
                    Button(onClick = { onStartBIntent(Intent.FLAG_ACTIVITY_SINGLE_TOP) }) {
                        Text(text = "B Single Top")
                    }
                }
                Row(horizontalArrangement = Arrangement.spacedBy(space = 8.dp)) {
                    Button(
                        onClick = {
                            onStartBIntent(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        }
                    ) {
                        Text(text = "B Clear Top")
                    }

                    Button(onClick = { onStartBIntent(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT) }) {
                        Text(text = "B Reorder")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun UserTaskScreenPreview() {
    DemosHostApplicationTheme {
        UserTaskScreen(
            trace = listOf(
                TraceNode("UserTaskDemoActivity", 123456, "START (NEW via onCreate)"),
                TraceNode("UserTaskDemoActivity", 234567, "START (REUSED via onNewIntent)"),
                TraceNode("UserTaskDemoActivity", 345678, "START (REUSED via onNewIntent)"),
            ),
            onStartAIntent = {},
            onStartBIntent = {},
            onBackIntent = {}
        )
    }
}

@Composable
fun TraceEntry(
    index: Int,
    node: TraceNode,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "${index + 1}. ${node.className} @${node.instanceHash}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = " >> ${node.launchNote}]",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun TraceEntryPreview() {
    DemosHostApplicationTheme {
        TraceEntry(
            index = 0,
            node = TraceNode("UserTaskDemoActivity", 123456, "START (NEW via onCreate)")
        )
    }
}