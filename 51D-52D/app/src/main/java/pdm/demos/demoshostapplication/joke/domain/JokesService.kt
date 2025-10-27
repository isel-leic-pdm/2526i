package pdm.demos.demoshostapplication.joke.domain

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pdm.demos.demoshostapplication.JOKE_APP_TAG
import java.net.URL
import kotlin.random.Random


/**
 * Service to get jokes.
 * An abstraction to be implemented by different approaches using different
 * data sources.
 */
interface JokesService {

    val joke: Flow<Joke>

    fun getJokes(): Flow<Joke>

    /**
     * Fetches a random joke and returns it.
     */
    suspend fun fetchJoke(): Joke
}

/**
 * A joke with its [text] content and [source] url.
 */
data class Joke(val text: String, val source: URL) {
    init {
        require(text.isNotBlank()) { "The joke's text must not be blank" }
    }
}

/**
 * Fake implementation of the JokesService. It returns a random joke from a pre-established list
 * of jokes.
 */
class FakeJokesService : JokesService {
    
    override val joke: Flow<Joke>
        get() = flow {
            while (true) {
                val index = Random.nextInt(from = 0, until = jokes.size)
                delay(5000) // Emit a new joke every 5 seconds
                emit(jokes[index])
            }
        }

    override fun getJokes(): Flow<Joke> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchJoke(): Joke {
        Log.v(JOKE_APP_TAG, "FakeJokesService.getJoke() started")
        val index = Random.nextInt(from = 0, until = jokes.size)
        delay(3000) // Simulate network delay
        val theJoke = jokes[index]
        Log.v(JOKE_APP_TAG, "FakeJokesService.getJoke() finishing")
        return theJoke
    }

    companion object {
        val jokes = listOf(
            Joke(
                text = "Chuck Norris didn't call the wrong number, you answered the wrong phone.",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes")
            ),
            Joke(
                text = "The dinosaurs once looked at Chuck Norris the wrong way" +
                        " and now we call them extinct.",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes"),
            ),
            Joke(
                text = "Somebody asked Chuck Norris how many press ups he could do, " +
                        "Chuck Norris replied \"all of them\".",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes"),
            ),
            Joke(
                text = "This graveyard looks overcrowded. People must be dying to get in there.",
                source = URL("https://www.keeplaughingforever.com/corny-dad-jokes")
            ),
            Joke(
                text = "Chuck Norris didn't call the wrong number, you answered the wrong phone.",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes")
            ),
            Joke(
                text = "The dinosaurs once looked at Chuck Norris the wrong way" +
                        " and now we call them extinct.",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes"),
            ),
            Joke(
                text = "Somebody asked Chuck Norris how many press ups he could do, " +
                        "Chuck Norris replied \"all of them\".",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes"),
            ),
            Joke(
                text = "This graveyard looks overcrowded. People must be dying to get in there.",
                source = URL("https://www.keeplaughingforever.com/corny-dad-jokes")
            ),
            Joke(
                text = "Chuck Norris didn't call the wrong number, you answered the wrong phone.",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes")
            ),
            Joke(
                text = "The dinosaurs once looked at Chuck Norris the wrong way" +
                        " and now we call them extinct.",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes"),
            ),
            Joke(
                text = "Somebody asked Chuck Norris how many press ups he could do, " +
                        "Chuck Norris replied \"all of them\".",
                source = URL("https://www.keeplaughingforever.com/chuck-norris-jokes"),
            ),
            Joke(
                text = "This graveyard looks overcrowded. People must be dying to get in there.",
                source = URL("https://www.keeplaughingforever.com/corny-dad-jokes")
            ),
        )
    }
}
