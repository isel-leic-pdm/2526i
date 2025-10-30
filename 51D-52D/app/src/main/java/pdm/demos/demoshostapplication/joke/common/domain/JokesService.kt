package pdm.demos.demoshostapplication.joke.common.domain

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
 * data sources. The service provides both push and pull operations.
 */
interface JokesService {

    /**
     * The flow of jokes produced by the service. This is a push operation.
     * Each time a new joke is available, it is emitted through the flow encapsulated in a [Result]
     * If an error occurs during the fetch, a failure [Result] is emitted instead.
     */
    val joke: Flow<Result<Joke>>

    /**
     * Fetches a random joke and returns it. This is a pull operation.
     * @return A [Result] wrapping the fetched [Joke] or an error if the fetch failed.
     */
    suspend fun fetchJoke(): Result<Joke>
}

/**
 * A joke with its [text] content and [source] url.
 */
data class Joke(val text: String, val source: URL) {
    init {
        require(text.isNotBlank()) { "The joke's text must not be blank" }
    }
}

private const val POLL_PERIOD_MILLIS = 10000L
private const val SIMULATED_NETWORK_DELAY_MILLIS = 3000L

/**
 * Fake implementation of the JokesService. It returns a random joke from a pre-established list
 * of jokes.
 */
class FakeJokesService : JokesService {

    override val joke: Flow<Result<Joke>>
        get() = flow {
            while (true) {
                val index = Random.nextInt(from = 0, until = jokes.size)
                delay(POLL_PERIOD_MILLIS)
                emit(Result.success(jokes[index]))
            }
        }

    override suspend fun fetchJoke(): Result<Joke> {
        Log.v(JOKE_APP_TAG, "FakeJokesService.getJoke() started")
        val index = Random.nextInt(from = 0, until = jokes.size)
        delay(SIMULATED_NETWORK_DELAY_MILLIS)
        val theJoke = jokes[index]
        Log.v(JOKE_APP_TAG, "FakeJokesService.getJoke() finishing")
        return Result.success(theJoke)
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
