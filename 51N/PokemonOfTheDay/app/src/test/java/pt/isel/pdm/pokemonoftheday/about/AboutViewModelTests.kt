package pt.isel.pdm.pokemonoftheday.about

import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteService
import pt.isel.pdm.pokemonoftheday.ui.about.AboutViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Before
import pt.isel.pdm.pokemonoftheday.AppBaseTest

@OptIn(ExperimentalCoroutinesApi::class)
class AboutViewModelTests : AppBaseTest() {


    @Test
    fun `About View Model should have null favourite when none configured`() {

        val vm = AboutViewModel(FakePokemonFavouriteService())

        val fav = vm.currentFavFlow.value

        assertNull(fav)
    }

    @Test
    fun `About View Model shows updated favourite`() = runTest {

        val service = FakePokemonFavouriteService()
        val vm = AboutViewModel(service)

        service.set(123)

        //
        //  In case service doesnt trigger a non null emit on currentFavFlow
        //
        withTimeout(10000)
        {
            val fav = vm.currentFavFlow.first { it != null }
            assertNotNull(fav)
        }

    }
}