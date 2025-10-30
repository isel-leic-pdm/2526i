package pt.isel.pdm.pokemonoftheday.ui.about

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import okhttp3.internal.wait
import pt.isel.pdm.pokemonoftheday.DependencyContainer
import pt.isel.pdm.pokemonoftheday.services.StorageService
import pt.isel.pdm.pokemonoftheday.ui.common.viewModelInit
import pt.isel.pdm.pokemonoftheday.ui.home.HomeViewModel
import pt.isel.pdm.pokemonoftheday.ui.theme.PokemonOfTheDayTheme
import java.util.Locale
import androidx.activity.viewModels
import kotlin.getValue

class AboutActivity : ComponentActivity() {

    val vm by viewModels<AboutViewModel> {
        viewModelInit {
            AboutViewModel(
                (application as DependencyContainer).storageService
            )
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Activity", "About")
        enableEdgeToEdge()
        setContent {
            AboutScreen(
                vm,
                { finish() }
            )
        }
    }

    companion object {
        fun navigate(ctx: Activity) {
            val intent = Intent(ctx, AboutActivity::class.java)
            ctx.startActivity(intent)
        }
    }

}

class AboutViewModel(serv: StorageService) : ViewModel(
) {
    val fav = serv.favourite.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        null
    )
}

