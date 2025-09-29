package pt.isel.pdm.crowdtally.ui.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import pt.isel.pdm.crowdtally.ui.theme.CrowdTallyTheme

@Composable
fun CrowdTallyScreen() {

    var maxCounter = mutableStateOf(5)
    var crowdCounter = mutableStateOf(0)
    CrowdTallyTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            /*
            CrowdTallyContent(
                crowdCounter = crowdCounter.value,
                onIncrement = {
                    crowdCounter.value++
                    Log.d("Button", "CrowCounter value is ${crowdCounter.value}")
                },
                onDecrement = {

                    crowdCounter.value--
                    Log.d("Button", "CrowCounter value is ${crowdCounter.value}")
                },
                incrementEnabled = true,
                decrementEnabled = crowdCounter.value > 0,
                modifier = Modifier.padding(innerPadding)
            )
             */

            CrowCounterConfigurator(
                maxCounterDefault = maxCounter.value,
                onCommit = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun CrowCounterConfigurator(
    maxCounterDefault: Int,
    onCommit: () -> Unit,
    modifier: Modifier = Modifier
) {

    Log.d("CrowCounterConfigurator", "Recomposed")
    var maxCounter = remember { mutableStateOf(maxCounterDefault) }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TextField(
            value = maxCounter.value.toString(),
            label = { Text("New Max Crowd") },
            onValueChange = { newValue ->
                //value changed
                maxCounter.value = newValue.toInt()
                Log.d("TextField", "Changing ${maxCounter.value}")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )

        Button(
            onClick = onCommit,
        ) {
            Text("Commit")
        }
    }
}


@Composable
fun CrowdTallyContent(
    crowdCounter: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    incrementEnabled: Boolean,
    decrementEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        ArrowButton(
            onClick = onIncrement,
            modifier = Modifier.rotate(-90f),
            enabled = incrementEnabled
        )


        Text(
            modifier = Modifier.padding(12.dp),
            text = crowdCounter.toString()
        )

        ArrowButton(
            onClick = onDecrement,
            enabled = decrementEnabled,
            modifier = Modifier.rotate(90f),
        )

    }
}