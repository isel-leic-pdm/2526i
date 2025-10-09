package pdm.demos.demoshostapplication.joke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pdm.demos.demoshostapplication.joke.domain.JokeOfDayService

/**
 * ViewModel for the Joke of the Day screen.
 * @property jokeService The service to get the joke of the day.
 */
class JokeOfDayScreenViewModel(val jokeService: JokeOfDayService) : ViewModel() {

    companion object {
        /**
         * Returns a factory to create a [JokeOfDayScreenViewModel] with the provided [JokeOfDayService].
         * @param service The service to get the joke of the day.
         */
        fun getFactory(service: JokeOfDayService) = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                if (modelClass.isAssignableFrom(JokeOfDayScreenViewModel::class.java)) {
                    JokeOfDayScreenViewModel(jokeService = service) as T
                }
                else throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}

