package pt.isel.pdm.pokemonoftheday.ui.firestorePlayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import pt.isel.pdm.pokemonoftheday.PokemonOfTheDayApplication
import pt.isel.pdm.pokemonoftheday.ui.BaseActivity
import pt.isel.pdm.pokemonoftheday.ui.common.viewModelInit
import pt.isel.pdm.pokemonoftheday.ui.theme.PokemonOfTheDayTheme

class FirestoreActivity : BaseActivity() {
    val viewModel by viewModels<FirestorePlaygroundViewModel> {
        viewModelInit {
            FirestorePlaygroundViewModel((application as PokemonOfTheDayApplication).firestorePlaygroundService)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokemonOfTheDayTheme {
                FirestorePlaygroundScreen(
                    viewModel = viewModel,
                    navigateBack = { finish() },
                    onSimpleModelClicked = {
                        SimpleModelDetailActivity.navigate(this, it)
                    }
                )
            }
        }
    }
}
