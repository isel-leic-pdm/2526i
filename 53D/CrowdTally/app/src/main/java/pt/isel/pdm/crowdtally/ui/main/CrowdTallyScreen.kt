package pt.isel.pdm.crowdtally.ui.main

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import pt.isel.pdm.crowdtally.domain.CrowdTallyCounter
import pt.isel.pdm.crowdtally.domain.decrement
import pt.isel.pdm.crowdtally.domain.increment
import pt.isel.pdm.crowdtally.domain.withNewMax
import pt.isel.pdm.crowdtally.ui.theme.CrowdTallyTheme


const val TestTagCounterText = "CrowdTallyScreen_Counter_Text"
const val TestTagIncrementButton = "CrowdTallyScreen_IncrementButton"
const val TestTagMaxCrowdInput = "CrowdTallyScreen_MaxCrowdInput"
const val TestTagConfigurationButton = "CrowdTallyScreen_ConfigurationButton"

sealed interface CrowdTallyScreenState {
    data class Counter(val counter: CrowdTallyCounter) : CrowdTallyScreenState
    data class Configuration(val maxCrowd: Int) : CrowdTallyScreenState
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrowdTallyScreen() {

    var state: CrowdTallyScreenState by remember {
        mutableStateOf(CrowdTallyScreenState.Counter(CrowdTallyCounter.Default))
    }

    CrowdTallyTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("CrowdTally") },
                    actions = {

                        when (val currState = state) {
                            is CrowdTallyScreenState.Counter -> {
                                Button(onClick = {
                                    state =
                                        CrowdTallyScreenState.Configuration(currState.counter.max)
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "edit"
                                    )
                                }
                            }

                            is CrowdTallyScreenState.Configuration -> {}
                        }


                    }
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->

            when (val viewState = state) {
                is CrowdTallyScreenState.Counter -> {
                    CrowdTallyContent(
                        crowdCounter = viewState.counter.current,
                        onIncrement = {
                            state = CrowdTallyScreenState.Counter(
                                viewState.counter.increment()
                            )
                            Log.d("Button", "CrowCounter value is ${viewState.counter.current}")
                        },
                        onDecrement = {
                            state = CrowdTallyScreenState.Counter(
                                viewState.counter.decrement()
                            )
                            Log.d("Button", "CrowCounter value is ${viewState.counter.current}")
                        },
                        incrementEnabled = viewState.counter.isFull == false,
                        decrementEnabled = viewState.counter.isEmpty == false,
                        modifier = Modifier.padding(innerPadding)
                    )
                }

                is CrowdTallyScreenState.Configuration -> {
                    CrowCounterConfigurator(
                        maxCounterDefault = viewState.maxCrowd,
                        onCommit = { newMaxCounter ->
                            state = CrowdTallyScreenState.Counter(
                                //CrowdTallyCounter.Default.withNewMax(newMaxCounter)
                                CrowdTallyCounter(0, newMaxCounter)
                            )
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

    }
}

@Composable
fun CrowCounterConfigurator(
    maxCounterDefault: Int,
    onCommit: (Int) -> Unit,
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
            modifier = Modifier.testTag(TestTagMaxCrowdInput)
        )

        Button(
            modifier = Modifier.testTag(TestTagConfigurationButton),
            onClick = {
                onCommit(maxCounter.value)
            },
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
            modifier = Modifier.rotate(-90f).testTag(TestTagIncrementButton),
            enabled = incrementEnabled
        )


        Text(
            modifier = Modifier.padding(12.dp).testTag(TestTagCounterText),
            text = crowdCounter.toString()
        )

        ArrowButton(
            onClick = onDecrement,
            enabled = decrementEnabled,
            modifier = Modifier.rotate(90f),
        )

    }
}