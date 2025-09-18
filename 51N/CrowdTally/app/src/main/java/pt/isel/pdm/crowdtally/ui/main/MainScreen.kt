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
import pt.isel.pdm.crowdtally.ui.theme.CrowdTallyTheme
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrowdTallyScreen(
) {
    var maxCrowd by remember { mutableStateOf(5) }
    var isMaxCrowdBeingEditted by remember { mutableStateOf(false) }
    var currentCrowd by remember() { mutableStateOf(0) }

    Log.d("CrowdTallyScreen", "recomposed")
    CrowdTallyTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("CrowdTally") },
                    actions = {
                        if (isMaxCrowdBeingEditted == false) {

                            Button(onClick = {
                                isMaxCrowdBeingEditted = true
                            }) {
                                Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                            }
                        } else {
                            Button(onClick = {
                                isMaxCrowdBeingEditted = false
                            }) {
                                Icon(imageVector = Icons.Default.Check, contentDescription = "")
                            }
                        }
                    }
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {

                if (isMaxCrowdBeingEditted) {
                    CrowdTallyConfiguratorContent(
                        maxCrowd = maxCrowd,
                        onMaxCrowdChanged = {
                            maxCrowd = it
                        }
                    )

                } else {
                    CrowdTallyContent(
                        maxCrowd = maxCrowd,
                        currentCrowd = currentCrowd,
                        onIncrement = {
                            currentCrowd++
                        },
                        onDecrement = {
                            currentCrowd--
                        }
                    )
                }
                /*
                 */
                /*
                CrowdTallyConfiguratorContent(
                    maxCrowd = 5
                )
                 */
            }
        }
    }
}
