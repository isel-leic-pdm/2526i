package pt.isel.pdm.pokemonoftheday

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

open class AppBaseTest {
    /**
     * Test dispatcher used to replace Dispatchers.Main in unit tests.
     *
     * Ensures that all coroutines running on Main (including viewModelScope),
     * or any custom Main-bound CoroutineScopes — execute in a controlled, deterministic way
     * during tests. Without this, StateFlows, or other suspending operations may
     * never emit or complete, causing flaky tests or timeouts.
     */
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}