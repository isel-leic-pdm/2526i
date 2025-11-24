package pt.isel.pdm.pokemonoftheday.ui.about

import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import org.junit.Test
import pt.isel.pdm.pokemonoftheday.AppBaseTest
import pt.isel.pdm.pokemonoftheday.services.FakePokemonFavouriteService

class AboutViewModelTests : AppBaseTest() {


    @Test
    fun `About View Model should have null favourite when none configured`() {

        val vm = AboutViewModel(FakePokemonFavouriteService())

        val fav = vm.quickFav.value

        assertNull(fav)
    }

    @Test
    fun `About View Model shows updated favourite`() = runTest {

        val service = FakePokemonFavouriteService()
        val vm = AboutViewModel(service)

        service.setFavourite(123)


        //
        //  In case service doesnt trigger a non null emit on currentFavFlow
        //
        withTimeout(10000)
        {
            val fav = vm.quickFav.first { it != null }
            assertNotNull(fav)
        }

    }
}