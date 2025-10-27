package pdm.demos.demoshostapplication.joke.http

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import pdm.demos.demoshostapplication.joke.domain.Joke
import pdm.demos.demoshostapplication.joke.domain.JokesService
import java.net.URL

/**
 * Implementation of the JokesService that fetches jokes from a remote server using HTTP.
 * @param client The HTTP client to use for fetching jokes.
 */
class IcanhazDadJokes(private val client: HttpClient) : JokesService {
    private val source = URL("https://icanhazdadjoke.com/")

    override suspend fun fetchJoke(): Joke {
        delay(1500)
        return client
            .get(url = source) { header("accept", "application/json") }
            .body<JokeDto>()
            .toJoke(source)
    }

    @Serializable
    private data class JokeDto(
        val id: String,
        val joke: String,
        val status: Int
    ) {
        fun toJoke(source: URL) = Joke(text = joke, source = source)
    }

    override val joke: Flow<Joke>
        get() = flow {
            while (true) {
                delay(10000) // Emit a new joke every 5 seconds
                emit(fetchJoke())
            }
        }

    override fun getJokes(): Flow<Joke> {
        TODO("Not yet implemented")
    }
}