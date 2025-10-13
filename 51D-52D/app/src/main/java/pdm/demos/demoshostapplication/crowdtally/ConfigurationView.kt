package pdm.demos.demoshostapplication.crowdtally

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.demoshostapplication.R
import pdm.demos.demoshostapplication.crowdtally.domain.isValidCapacity
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

const val CONFIGURE_VIEW_TAG = "configure_view"
const val SAVE_BUTTON_TAG = "save_button"
const val CANCEL_BUTTON_TAG = "cancel_button"
const val CAPACITY_TEXT_FIELD_TAG = "capacity_text_field"

/**
 * Composable that corresponds to the configuration view of the crowd tally screen.
 * @param state The state associated to the configuration view.
 * @param onSaveIntent The callback to be invoked when the user intends to change the configuration.
 * @param modifier The modifier to be applied to this composable.
 */
@Composable
fun ConfigurationView(
    state: CrowdTallyScreenState.Configuration,
    onSaveIntent: (Int) -> Unit,
    onCancelIntent: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(all = 16.dp).testTag(tag = CONFIGURE_VIEW_TAG),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var currentCapacity by rememberSaveable { mutableIntStateOf(value = state.capacity) }
        TextField(
            value = "$currentCapacity",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { currentCapacity = it.toIntOrNull() ?: 0 },
            singleLine = true,
            supportingText = { Text(text = stringResource(id = R.string.crowd_tally_capacity_input_hint)) },
            label = { Text(text = stringResource(id = R.string.crowd_tally_capacity_label)) },
            modifier = Modifier.testTag(tag = CAPACITY_TEXT_FIELD_TAG)
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { onSaveIntent(currentCapacity) },
                enabled = currentCapacity.isValidCapacity() && currentCapacity != state.capacity,
                modifier = Modifier.testTag(tag = SAVE_BUTTON_TAG)
            ) {
                Text(text = stringResource(id = R.string.crowd_tally_save_button_text))
            }
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Button(
                onClick = onCancelIntent,
                modifier = Modifier.testTag(tag = CANCEL_BUTTON_TAG)
            ) {
                Text(text = stringResource(id = R.string.crowd_tally_cancel_button_text))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConfigurationViewPreview() {
    DemosHostApplicationTheme {
        ConfigurationView(
            state = CrowdTallyScreenState.Configuration(capacity = 10),
            onSaveIntent = { },
            onCancelIntent = { }
        )
    }
}