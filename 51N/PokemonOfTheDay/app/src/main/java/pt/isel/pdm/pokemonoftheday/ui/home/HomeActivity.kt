package pt.isel.pdm.pokemonoftheday.ui.home

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import pt.isel.pdm.pokemonoftheday.DependencyContainer
import pt.isel.pdm.pokemonoftheday.services.FakePokedexService
import pt.isel.pdm.pokemonoftheday.ui.BaseActivity
import pt.isel.pdm.pokemonoftheday.ui.about.AboutActivity
import pt.isel.pdm.pokemonoftheday.ui.common.setAppLocale
import pt.isel.pdm.pokemonoftheday.ui.common.viewModelInit
import pt.isel.pdm.pokemonoftheday.ui.firestorePlayground.FirestoreActivity
import java.util.Locale

class HomeActivity : BaseActivity() {
    /*
    val vm by viewModels<HomeViewModel>(factoryProducer = {
        HomeViewModelFactory(FakePokedexService())
    })
*/
    val vm by viewModels<HomeViewModel> {
        viewModelInit {
            HomeViewModel(
                (application as DependencyContainer).pokedexService,
                (application as DependencyContainer).pokemonFavouriteService
            )


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.refreshPokemonOfTheDay()

        enableEdgeToEdge()
        setContent {
            HomeScreen(
                navToAbout = {
                    navigate<AboutActivity>()
                    //AboutActivity.navigate(this)
                },
                navToFirestorePlayground = {
                    navigate<FirestoreActivity> ()
                },
                viewModel = vm
            )
        }
    }


}


