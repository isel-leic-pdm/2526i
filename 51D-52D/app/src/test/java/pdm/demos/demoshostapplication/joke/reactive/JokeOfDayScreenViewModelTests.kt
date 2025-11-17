package pdm.demos.demoshostapplication.joke.reactive

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test
import pdm.demos.demoshostapplication.joke.common.domain.Joke
import pdm.demos.demoshostapplication.joke.common.domain.JokesService

class JokeOfDayScreenViewModelTests {

    @Test
    fun initially_its_in_idle_state() = runTest {
        val sut = JokeOfDayScreenViewModel(jokeService = FakeJokesService())
        var result: JokeOfDayScreenState? = null
        val latch = SuspendingLatch()

        val collectorJob = launch {
            sut.currentState.collect {
                result = it
                latch.open()
            }
        }

        latch.await()
        collectorJob.cancel()

        assert(result is JokeOfDayScreenState.Idle) {
            "Expected Idle state, but got $result"
        }
    }

    @Test
    fun once_started_changes_to_success_state_when_joke_is_received() = runTest {
        val sut = JokeOfDayScreenViewModel(jokeService = FakeJokesService())
        val latch = SuspendingLatch()
        var result: JokeOfDayScreenState? = null

        val collectorJob = launch {
            sut.currentState.collect {
                result = it
                if (it is JokeOfDayScreenState.Success) {
                    latch.open()
                }
            }
        }

        latch.await()
        collectorJob.cancel()

        assert(result is JokeOfDayScreenState.Success) {
            "Expected second state to be Success, but got ${result}"
        }
    }

    @Test
    fun once_started_changes_to_error_state_when_joke_fetch_fails() = runTest {
        val sut = JokeOfDayScreenViewModel(jokeService = FailingFakeJokesService())
        val latch = SuspendingLatch()
        var result: JokeOfDayScreenState? = null

        val collectorJob = launch {
            sut.currentState.collect {
                result = it
                if (it is JokeOfDayScreenState.Error) {
                    latch.open()
                }
            }
        }

        latch.await()
        collectorJob.cancel()

        assert(result is JokeOfDayScreenState.Error) {
            "Expected second state to be Error, but got ${result}"
        }
    }
}

/**
 * A fake JokesService that always returns a successful joke.
 */
private class FakeJokesService : JokesService {
    override val joke: Flow<Result<Joke>> = flow {
        // The delay, regardless of its value, is required to ensure a suspension point here so that
        // the coroutine running the test may start collecting before the joke is emitted.
        delay(1000)
        val joke = Joke(
            text = "Why did the scarecrow win an award? Because he was outstanding in his field!",
            source = java.net.URL("https://example.com/joke1")
        )
        emit(Result.success(joke))
    }
    override suspend fun fetchJoke(): Result<Joke> { TODO("Not required for tests") }
}

/**
 * A fake JokesService that always fails to return a joke.
 */
private class FailingFakeJokesService : JokesService {
    override val joke: Flow<Result<Joke>> = flow {
        emit(Result.failure(Exception("Failed to fetch joke")))
    }
    override suspend fun fetchJoke(): Result<Joke> { TODO("Not required for tests") }
}

