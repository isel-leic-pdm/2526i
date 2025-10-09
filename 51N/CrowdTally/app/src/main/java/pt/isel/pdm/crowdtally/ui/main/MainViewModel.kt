package pt.isel.pdm.crowdtally.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import pt.isel.pdm.crowdtally.domain.CrowdTallyCounter
import pt.isel.pdm.crowdtally.domain.decrement
import pt.isel.pdm.crowdtally.domain.increment

sealed interface CrowdTallyScreenState {
    data class Configurator(val maxCrowd: Int) : CrowdTallyScreenState
    data class Counter(val counter: CrowdTallyCounter) : CrowdTallyScreenState
}

class MainViewModel : ViewModel() {

    var screenState: CrowdTallyScreenState by mutableStateOf(
        CrowdTallyScreenState.Counter(
            CrowdTallyCounter.Default
        )
    )

    fun incrementPeople() {

        when (val state = screenState) {
            is CrowdTallyScreenState.Counter -> {
                screenState =
                    CrowdTallyScreenState.Counter(state.counter.increment())
            }
            else -> {
                throw Exception("cant increment when state is not counter")
            }
        }
    }

    fun decrementPeople() {

        when (val state = screenState) {
            is CrowdTallyScreenState.Counter -> {
                screenState =
                    CrowdTallyScreenState.Counter(state.counter.decrement())
            }
            else -> {
                throw Exception("cant decrenebt when state is not counter")
            }
        }

    }

    fun stopConfiguration(maxCrowd: Int) {

        when (screenState) {
            is CrowdTallyScreenState.Configurator -> {
                screenState =
                    CrowdTallyScreenState.Counter(CrowdTallyCounter(0, maxCrowd))
            }
            else -> {
                throw Exception("cant change max crowd when state is not config")
            }
        }



    }

    fun startConfiguration() {

        when (val state = screenState) {
            is CrowdTallyScreenState.Counter -> {
                screenState =
                    CrowdTallyScreenState.Configurator(state.counter.maxCrowd)
            }
            else -> {
                throw Exception("cant change to configuration if not on counter")
            }
        }

    }


    override fun onCleared() {
        super.onCleared()
    }

}