package pt.isel.pdm.crowdtally.ui.main

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pt.isel.pdm.crowdtally.ui.theme.CrowdTallyTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrowdTallyScreen(
    viewModel: MainViewModel
) {


    Log.d("CrowdTallyScreen", "recomposed")
    CrowdTallyTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("CrowdTally") },
                    actions = {

                        when (val state = viewModel.screenState) {
                            is CrowdTallyScreenState.Counter -> {
                                Button(onClick = {
                                    viewModel.startConfiguration()
                                }) {
                                    Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                                }
                            }

                            is CrowdTallyScreenState.Configurator -> {

                            }
                        }

                    })
            }) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {


                when (val currState = viewModel.screenState) {
                    is CrowdTallyScreenState.Configurator -> {
                        CrowdTallyConfiguratorContent(
                            maxCrowd = currState.maxCrowd,
                            onMaxCrowdChanged = { maxCrowd ->
                                viewModel.stopConfiguration(maxCrowd)
                                })
                    }

                    is CrowdTallyScreenState.Counter -> {
                        CrowdTallyContent(
                            maxCrowd = currState.counter.maxCrowd,
                            currentCrowd = currState.counter.currentCrowd,
                            onIncrement = {
                               viewModel.incrementPeople()
                            },
                            onDecrement = {
                                viewModel.decrementPeople()
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

@Composable
fun stopConfiguration(x0: Int) {
    TODO("Not yet implemented")
}

