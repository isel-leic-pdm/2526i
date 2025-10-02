package pt.isel.pdm.crowdtally.ui.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val CROWDTALLYCONTENT_INC_BUTTON = "CrowdTallyContent.incbutton"
const val CROWDTALLYCONFIGURATORCONTENT_TEXTFIELD = "CrowdTallyConfiguratorContent.TextField"

@Composable
fun CrowdTallyConfiguratorContent(
    maxCrowd: Int,
    onMaxCrowdChanged: (Int) -> Unit
) {
    Column {
        TextField(
            value = maxCrowd.toString(),
            label = { Text("New Max Crowd") },
            onValueChange = { newValue ->
                Log.d("TextField", newValue)
                if (newValue.isNullOrBlank() == false) {
                    val nr = newValue.toIntOrNull()
                    if (nr != null)
                        onMaxCrowdChanged(nr.toInt())
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.testTag(CROWDTALLYCONFIGURATORCONTENT_TEXTFIELD)
        )
    }

}

@Composable
fun CrowdTallyContent(
    currentCrowd: Int,
    maxCrowd: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    incEnabled : Boolean,
    decEnabled : Boolean
) {
    Log.d("CrowdTallyContent", "recomposed")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {


        ArrowButton(
            enabled = incEnabled,
            onClick = onIncrement,
            modifier = Modifier.rotate(-90f)
                .testTag(CROWDTALLYCONTENT_INC_BUTTON)
        )
        Text(
            text = currentCrowd.toString(),
            fontSize = 40.sp,
            modifier = Modifier.padding(vertical = 12.dp)
        )


        ArrowButton(
            enabled = decEnabled,
            onClick = onDecrement,
            modifier = Modifier.rotate(90f)
        )

    }
}

@Composable
fun ArrowButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "PlayArrow"
        )
    }
}