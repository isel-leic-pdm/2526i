package pdm.demos.demoshostapplication.pubsub.publish

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.demoshostapplication.R
import pdm.demos.demoshostapplication.pubsub.common.MessageBoard
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

@Composable
fun PublishForm(
    loading: Boolean,
    error: String?,
    onPublish: (messageBoard: MessageBoard) -> Unit,
    validateData: (boardId: String, message: String) -> Boolean,
    modifier: Modifier = Modifier
) {
    var currBoardId by rememberSaveable { mutableStateOf("") }
    var currMessage by rememberSaveable { mutableStateOf("") }
    PublishFormStateless(
        loading = loading,
        error = error,
        boardId = currBoardId,
        message = currMessage,
        isDataValid = validateData(currBoardId, currMessage),
        onBoardIdChange = { currBoardId = it },
        onMessageChange = { currMessage = it },
        onPublish = { boardId, message -> onPublish(MessageBoard(boardId, message)) },
        modifier
    )
}

@Composable
fun PublishFormStateless(
    loading: Boolean,
    error: String?,
    boardId: String,
    message: String,
    isDataValid: Boolean,
    onBoardIdChange: (String) -> Unit,
    onMessageChange: (String) -> Unit,
    onPublish: (boardId: String, message: String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = boardId,
            onValueChange = onBoardIdChange,
            label = { Text(text = stringResource(R.string.pub_sub_board_Id_input_label)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = message,
            onValueChange = onMessageChange,
            label = { Text(text = stringResource(R.string.pub_sub_message_input_label)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { if (isDataValid) onPublish(boardId, message) }),
            modifier = Modifier.fillMaxWidth()
        )

        if (!error.isNullOrBlank()) {
            Text(error, color = MaterialTheme.colorScheme.error)
        }

        Button(
            onClick = { onPublish(boardId, message) },
            enabled = isDataValid && !loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (loading) {
                CircularProgressIndicator(Modifier.size(16.dp), strokeWidth = 2.dp)
                Spacer(Modifier.width(8.dp))
            }
            Text(text = stringResource(R.string.pub_sub_publish_button))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PublishFormInvalidDataPreview() {
    DemosHostApplicationTheme {
        PublishFormStateless(
            loading = false,
            error = null,
            boardId = "some-board-id",
            message = "Lets go!",
            isDataValid = false,
            onBoardIdChange = {},
            onMessageChange = {},
            onPublish = { _, _ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PublishFormValidDataPreview() {
    DemosHostApplicationTheme {
        PublishFormStateless(
            loading = false,
            error = null,
            boardId = "some-board-id",
            message = "Lets go!",
            isDataValid = true,
            onBoardIdChange = { },
            onMessageChange = { },
            onPublish = { _, _ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PublishFormSubmittingPreview() {
    DemosHostApplicationTheme {
        PublishFormStateless(
            loading = true,
            error = null,
            boardId = "some-board-id",
            message = "Lets go!",
            isDataValid = true,
            onBoardIdChange = { },
            onMessageChange = { },
            onPublish = { _, _ -> }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PublishFormPreviewError() {
    DemosHostApplicationTheme {
        PublishFormStateless(
            loading = false,
            error = "Failed to publish the message",
            boardId = "",
            message = "",
            isDataValid = false,
            onBoardIdChange = { },
            onMessageChange = { },
            onPublish = { _, _ -> }
        )
    }
}
