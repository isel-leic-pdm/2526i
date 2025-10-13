package pdm.demos.demoshostapplication.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

const val ERROR_ALERT_TAG = "ErrorAlertTestTag"
const val ERROR_ALERT_DISMISS_BUTTON_TAG = "ErrorAlertDismissButtonTestTag"

@Composable
fun ErrorAlert(
    @StringRes title: Int,
    @StringRes message: Int,
    @StringRes buttonText: Int,
    onDismiss: () -> Unit = { }
) {
    ErrorAlertImpl(
        title = stringResource(id = title),
        message = stringResource(id = message),
        buttonText = stringResource(id = buttonText),
        onDismiss = onDismiss
    )
}

@Composable
fun ErrorAlertImpl(
    title: String,
    message: String,
    buttonText: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            OutlinedButton(
                onClick = onDismiss,
                modifier = Modifier.testTag(ERROR_ALERT_DISMISS_BUTTON_TAG)
            ) {
                Text(text = buttonText)
            }
        },
        title = { Text(text = title) },
        titleContentColor = MaterialTheme.colorScheme.error,
        iconContentColor = MaterialTheme.colorScheme.error,
        text = { Text(text = message) },
        icon = {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Warning"
            )
        },
        modifier = Modifier.testTag(ERROR_ALERT_TAG)
    )
}

@Preview(showBackground = true)
@Composable
private fun ErrorAlertImplPreview() {
    DemosHostApplicationTheme {
        ErrorAlertImpl(
            title = "Error while ...",
            message = "Could not ...",
            buttonText = "OK",
            onDismiss = { }
        )
    }
}
