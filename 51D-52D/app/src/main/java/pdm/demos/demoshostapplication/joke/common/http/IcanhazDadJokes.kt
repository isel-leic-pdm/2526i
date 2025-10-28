package pdm.demos.demoshostapplication.joke.common.http

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import pdm.demos.demoshostapplication.joke.common.domain.Joke
import pdm.demos.demoshostapplication.joke.common.domain.JokesService
import java.net.URL

private const val ICANHAZ_POLL_PERIOD_MILLIS = 10000L
private const val ICANHAZ_SIMULATED_NETWORK_DELAY_MILLIS = 1500L

/**
 * Implementation of the JokesService that fetches jokes from a remote server using HTTP.
 * @param client The HTTP client to use for fetching jokes.
 */
class IcanhazDadJokes(private val client: HttpClient) : JokesService {
    private val source = URL("https://icanhazdadjoke.com/")

    override val joke: Flow<Result<Joke>>
        get() = flow {
            while (true) {
                emit(fetchJoke())
                delay(ICANHAZ_POLL_PERIOD_MILLIS)
            }
        }

    override suspend fun fetchJoke(): Result<Joke> =
        try {
            delay(ICANHAZ_SIMULATED_NETWORK_DELAY_MILLIS)
            val joke = client
                .get(url = source) { header("accept", "application/json") }
                .body<JokeDto>()
                .toJoke(source)
            Result.success(joke)
        } catch (e: Exception) {
            Result.failure(e)
        }

    @Serializable
    private data class JokeDto(
        val id: String,
        val joke: String,
        val status: Int
    ) {
        fun toJoke(source: URL) = Joke(text = joke, source = source)
    }
}