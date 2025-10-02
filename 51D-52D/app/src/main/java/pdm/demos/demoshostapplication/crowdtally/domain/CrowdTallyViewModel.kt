package pdm.demos.demoshostapplication.crowdtally.domain

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/**
 * Represents the different states of the crowd tally screen.
 *
 * DESIGN NOTE: Each screen state corresponds to a different view bearing the name of the state.
 * The user can only transition from one state to another through specific actions. For example,
 * selecting the configuration option, saving the configuration or cancelling it.
 */
sealed interface CrowdTallyScreenState {

    /**
     * State associated to the configuration view of the crowd tally screen.
     * @param capacity The current counter capacity.
     */
    data class Configuration(val capacity: Int) : CrowdTallyScreenState

    /**
     * State associated to the counting view of the crowd tally screen.
     * @param counter The initial [CrowdCounter] to be used for counting.
     */
    data class Counting(val counter: CrowdCounter) : CrowdTallyScreenState
}

/**
 * ViewModel that holds the current state of the Crowd Tally Screen.
 *
 * DESIGN NOTE:
 * Each screen has an associated ViewModel that holds its state. The view model is also responsible
 * for interacting with the remaining application layers in order to accomplish the screen's use
 * cases.
 */
class CrowdTallyViewModel : ViewModel() {

    var currentState: CrowdTallyScreenState by
        mutableStateOf(value = CrowdTallyScreenState.Counting(counter = CrowdCounter()))

    /**
     * Transitions the screen to the configuration state if it is currently counting.
     */
    fun configure() {
        val state = currentState
        if (state is CrowdTallyScreenState.Counting) {
            currentState = CrowdTallyScreenState.Configuration(capacity = state.counter.capacity)
        }
    }

    /**
     * Cancels the configuration and transitions the screen back to the counting state if it is
     * currently configuring.
     */
    fun cancelConfiguration() {
        val state = currentState
        if (state is CrowdTallyScreenState.Configuration) {
            currentState = CrowdTallyScreenState.Counting(counter = CrowdCounter())
        }
    }

    /**
     * Saves the new capacity and transitions the screen back to the counting state if it is
     * currently configuring.
     * @param newCapacity The new capacity to be set.
     */
    fun saveConfiguration(newCapacity: Int) {
        val state = currentState
        if (state is CrowdTallyScreenState.Configuration) {
            currentState = CrowdTallyScreenState.Counting(
                counter = CrowdCounter(capacity = newCapacity)
            )
        }
    }
}