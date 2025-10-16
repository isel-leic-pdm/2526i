package pdm.demos.demoshostapplication.joke

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pdm.demos.demoshostapplication.joke.domain.Joke
import pdm.demos.demoshostapplication.joke.domain.JokesService

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

    var currentState: JokeOfDayScreenState by mutableStateOf(value = JokeOfDayScreenState.Idle)

    init {
        viewModelScope.launch {
            while (true) {
                if (currentState !is JokeOfDayScreenState.Loading) {
                    internalFetchJoke()
                }
                delay(10000)
            }
        }
    }

    private suspend fun internalFetchJoke(): Unit {
        currentState = try {
            currentState = JokeOfDayScreenState.Loading
            jokeService.getJoke().let {
                JokeOfDayScreenState.Success(it)
            }
        } catch (e: Exception) {
            JokeOfDayScreenState.Error(e)
        }
    }

    /**
     * Fetches a new joke from the service and updates the screen state accordingly.
     */
    fun fetchJoke() {
        if (currentState !is JokeOfDayScreenState.Loading) {
            viewModelScope.launch {
                internalFetchJoke()
            }
        }
    }

    /**
     * Resets the screen state to idle.
     */
    fun resetToIdle() {
        if (currentState !is JokeOfDayScreenState.Loading) {
            currentState = JokeOfDayScreenState.Idle
        }
    }
}

