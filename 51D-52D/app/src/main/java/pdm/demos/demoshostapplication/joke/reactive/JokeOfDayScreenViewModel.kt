package pdm.demos.demoshostapplication.joke.reactive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pdm.demos.demoshostapplication.joke.common.domain.Joke
import pdm.demos.demoshostapplication.joke.common.domain.JokesService

/**
 * Represents the state of the joke of day screen (reactive version).
 */
sealed interface JokeOfDayScreenState {
    data object Idle : JokeOfDayScreenState
    data class Success(val joke: Joke) : JokeOfDayScreenState
    data class Error(val lastJoke: Joke?, val exception: Throwable) : JokeOfDayScreenState
}

/**
 * ViewModel for the Joke of the Day screen  (reactive version)
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
    private val _currentState = MutableStateFlow<JokeOfDayScreenState>(JokeOfDayScreenState.Idle)

    /**
     * Public read-only access to the current state of the screen.
     */
    val currentState: StateFlow<JokeOfDayScreenState>
        get() = _currentState.asStateFlow()

    /**
     * When instantiated, starts collecting jokes from the service to update the screen state.
     *
     * DESIGN NOTE: The ViewModel starts collecting from the service as soon as it is created.
     * This is a poor design because it makes testing more difficult than needed.
     * A better design would be to start the collection explicitly through a method call,
     * allowing tests to control when the collection starts.
     */
    init {
        viewModelScope.launch {
            jokeService.joke.collect { result ->
                _currentState.value = try {
                    JokeOfDayScreenState.Success(joke = result.getOrThrow())
                } catch (e: Exception) {
                    val lastJoke = (currentState.value as? JokeOfDayScreenState.Success)?.joke
                    JokeOfDayScreenState.Error(lastJoke, exception = e)
                }
            }
        }
    }
}

