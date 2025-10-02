package pdm.demos.demoshostapplication.crowdtally

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pdm.demos.demoshostapplication.crowdtally.domain.CrowdCounter
import pdm.demos.demoshostapplication.crowdtally.domain.CrowdTallyScreenState
import pdm.demos.demoshostapplication.ui.RoundButton
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

const val COUNTING_VIEW_TAG = "counting_view"
const val COUNTER_VALUE_TAG = "counter_value"
const val PLUS_BUTTON_TAG = "plus_button"
const val MINUS_BUTTON_TAG = "minus_button"

@Composable
fun CountingView(
    state: CrowdTallyScreenState.Counting,
    modifier: Modifier = Modifier
) {
    var counter by remember { mutableStateOf(value = state.counter) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize().testTag(tag = COUNTING_VIEW_TAG),
    ) {
        Text(
            text = counter.value.toString(),
            fontSize = 72.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 36.dp).testTag(tag = COUNTER_VALUE_TAG)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            RoundButton(
                symbol = '-',
                enabled = counter.isNotAtMinimum(),
                onClick = { counter-- },
                modifier = Modifier.testTag(tag = MINUS_BUTTON_TAG)
            )
            RoundButton(
                symbol = '+',
                enabled = counter.isNotAtMaximum(),
                onClick = { counter++ },
                modifier = Modifier.testTag(tag = PLUS_BUTTON_TAG)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CountingViewPreview() {
    DemosHostApplicationTheme {
        CountingView(state = CrowdTallyScreenState.Counting(counter = CrowdCounter(value = 10)))
    }
}
