package pt.isel.pdm.crowdtally.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import pt.isel.pdm.crowdtally.domain.CrowdTallyCounter
import pt.isel.pdm.crowdtally.domain.decrement
import pt.isel.pdm.crowdtally.domain.increment
import pt.isel.pdm.crowdtally.ui.main.CrowdTallyScreenState.*

sealed interface CrowdTallyScreenState {
    data class Counter(val counter: CrowdTallyCounter) : CrowdTallyScreenState
    data class Configuration(val maxCrowd: Int) : CrowdTallyScreenState
}

class CrowdTallyViewModel : ViewModel() {

    var state: CrowdTallyScreenState by mutableStateOf(
        CrowdTallyScreenState.Counter(
            CrowdTallyCounter.Default
        )
    )

    fun incrementPeople() {
        when (val screenState = state) {
            is CrowdTallyScreenState.Counter -> {
                state = Counter(
                    screenState.counter.increment()
                )
            }

            else -> throw Exception("Should not happen")
        }
    }

    fun decrementPeople() {
        when (val screenState = state) {
            is CrowdTallyScreenState.Counter -> {
                state = Counter(
                    screenState.counter.decrement()
                )
            }

            else -> throw Exception("Should not happen")
        }
    }

    fun startConfiguration() {
        when (val screenState = state) {
            is CrowdTallyScreenState.Counter -> {
                state =
                    CrowdTallyScreenState.Configuration(screenState.counter.max)
            }

            else -> throw Exception("Should not happen")
        }

    }

    fun stopConfiguration(maxValue: Int) {
        when (state) {
            is CrowdTallyScreenState.Configuration -> {
                state = CrowdTallyScreenState.Counter(CrowdTallyCounter(0, maxValue))
            }

            else -> throw Exception("Should not happen")
        }
    }
}