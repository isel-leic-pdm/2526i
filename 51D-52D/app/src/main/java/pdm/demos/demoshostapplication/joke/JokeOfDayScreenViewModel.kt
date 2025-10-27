package pdm.demos.demoshostapplication.joke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
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

    // var currentState: JokeOfDayScreenState by mutableStateOf(value = JokeOfDayScreenState.Idle)

    val currentStateFlow = MutableStateFlow<JokeOfDayScreenState>(JokeOfDayScreenState.Idle)

    init {
        viewModelScope.launch {
            jokeService.joke.collect {
                currentStateFlow.value = JokeOfDayScreenState.Success(joke = it)
            }
        }
    }

    private suspend fun internalFetchJoke() {
        currentStateFlow.value = try {
            currentStateFlow.value = JokeOfDayScreenState.Loading
            jokeService.fetchJoke().let {
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
        if (currentStateFlow.value !is JokeOfDayScreenState.Loading) {
            viewModelScope.launch {
                internalFetchJoke()
            }
        }
    }

    /**
     * Resets the screen state to idle.
     */
    fun resetToIdle() {
        if (currentStateFlow.value !is JokeOfDayScreenState.Loading) {
            currentStateFlow.value = JokeOfDayScreenState.Idle
        }
    }
}

