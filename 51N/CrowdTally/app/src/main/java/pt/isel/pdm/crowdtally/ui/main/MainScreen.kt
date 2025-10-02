package pt.isel.pdm.crowdtally.ui.main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import pt.isel.pdm.crowdtally.domain.CrowdTallyCounter
import pt.isel.pdm.crowdtally.domain.changeMaxCrowd
import pt.isel.pdm.crowdtally.domain.decrement
import pt.isel.pdm.crowdtally.domain.increment
import pt.isel.pdm.crowdtally.ui.theme.CrowdTallyTheme
import kotlin.math.max

sealed interface CrowdTallyScreenState {
    data class Configurator(val maxCrowd: Int) : CrowdTallyScreenState
    data class Counter(val counter: CrowdTallyCounter) : CrowdTallyScreenState
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrowdTallyScreen(
) {

    var screenState: CrowdTallyScreenState by remember {
        mutableStateOf(
            CrowdTallyScreenState.Counter(
                CrowdTallyCounter.Default
            )
        )
    }

    Log.d("CrowdTallyScreen", "recomposed")
    CrowdTallyTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("CrowdTally") },
                    actions = {

                        when (val state = screenState) {
                            is CrowdTallyScreenState.Counter -> {
                                Button(onClick = {
                                    screenState =
                                        CrowdTallyScreenState.Configurator(state.counter.maxCrowd)
                                }) {
                                    Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                                }
                            }

                            is CrowdTallyScreenState.Configurator -> {
                                Button(onClick = {
                                    screenState = CrowdTallyScreenState.Counter(
                                        CrowdTallyCounter(
                                            0,
                                            state.maxCrowd
                                        )
                                    )
                                }) {
                                    Icon(imageVector = Icons.Default.Check, contentDescription = "")
                                }
                            }
                        }

                    })
            }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {


                when (val currState = screenState) {
                    is CrowdTallyScreenState.Configurator -> {
                        CrowdTallyConfiguratorContent(
                            maxCrowd = currState.maxCrowd,
                            onMaxCrowdChanged = { maxCrowd ->
                                screenState =
                                    CrowdTallyScreenState.Configurator(maxCrowd)
                            })
                    }

                    is CrowdTallyScreenState.Counter -> {
                        CrowdTallyContent(
                            maxCrowd = currState.counter.maxCrowd,
                            currentCrowd = currState.counter.currentCrowd,
                            onIncrement = {
                                screenState =
                                    CrowdTallyScreenState.Counter(currState.counter.increment())
                            },
                            onDecrement = {
                                screenState =
                                    CrowdTallyScreenState.Counter(currState.counter.decrement())

                            },
                            incEnabled = currState.counter.isFull == false,
                            decEnabled = currState.counter.isEmpty == false
                        )
                    }
                }

            }
        }
    }
}

