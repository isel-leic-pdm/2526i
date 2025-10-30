package pdm.demos.demoshostapplication.joke.pull

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pdm.demos.demoshostapplication.joke.common.domain.Joke
import pdm.demos.demoshostapplication.joke.common.domain.JokesService

const val POLL_PERIOD_MILLIS = 10000L

/**
 * Represents the state of the joke of day screen.
 */
sealed interface JokeOfDayScreenState {
    data object Idle : JokeOfDayScreenState
    data object Loading : JokeOfDayScreenState
    data class Success(val joke: Joke) : JokeOfDayScreenState
    data class Error(val exception: Throwable) : JokeOfDayScreenState
}

/**
 * ViewModel for the Joke of the Day screen.
 * @property jokeService The service to get the joke of the day.
 */
class JokeOfDayScreenViewModel(val jokeService: JokesService) : ViewModel() {

    companion object {
        /**
         * Returns a factory to create a [JokeOfDayScreenViewModel] with the provided [JokesService].
         * @param service The service to get the joke of the day.
         */
        fun getFactory(service: JokesService) = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                if (modelClass.isAssignableFrom(JokeOfDayScreenViewModel::class.java)) {
                    JokeOfDayScreenViewModel(jokeService = service) as T
                }
                else throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    /**
     * The current state of the screen.
     */
    private var _currentState by mutableStateOf<JokeOfDayScreenState>(JokeOfDayScreenState.Idle)

    /**
     * Public read-only access to the current state of the screen.
     */
    val currentState: JokeOfDayScreenState
        get() = _currentState

    /**
     * When instantiated, starts collecting jokes from the service to update the screen state.
     */
    init {
        viewModelScope.launch {
            while (true) {
                delay(timeMillis = POLL_PERIOD_MILLIS)
                internalFetchJoke()
            }
        }
    }

    private suspend fun internalFetchJoke() {
        _currentState = try {
            _currentState = JokeOfDayScreenState.Loading
            jokeService.fetchJoke().getOrThrow().let {
                JokeOfDayScreenState.Success(joke = it)
            }
        } catch (e: Exception) {
            JokeOfDayScreenState.Error(exception = e)
        }
    }

    /**
     * Fetches a new joke from the service and updates the screen state accordingly.
     */
    fun fetchJoke() {
        if (_currentState !is JokeOfDayScreenState.Loading) {
            viewModelScope.launch {
                internalFetchJoke()
            }
        }
    }

    /**
     * Resets the screen state to idle.
     */
    fun resetToIdle() {
        if (_currentState !is JokeOfDayScreenState.Loading) {
            _currentState = JokeOfDayScreenState.Idle
        }
    }
}

