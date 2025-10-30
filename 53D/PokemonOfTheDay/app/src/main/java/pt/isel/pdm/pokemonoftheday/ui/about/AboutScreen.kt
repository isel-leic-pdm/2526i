package pt.isel.pdm.pokemonoftheday.ui.about

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import pt.isel.pdm.pokemonoftheday.R
import pt.isel.pdm.pokemonoftheday.ui.common.CustomAppTopBar
import pt.isel.pdm.pokemonoftheday.ui.common.NavigationActions
import pt.isel.pdm.pokemonoftheday.ui.theme.PokemonOfTheDayTheme
import java.util.Locale


@Composable
fun AboutScreen(
    vm: AboutViewModel,
    backPressed: () -> Unit
) {

    val pokeId = vm.fav.collectAsState().value
    PokemonOfTheDayTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CustomAppTopBar(
                    title = R.string.title_activity_about,
                    navActions = NavigationActions(
                        onBackAction = backPressed
                    )
                )
            }
        )

        { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.about_text),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                    )

                    if (pokeId != null)
                        Text(text = "Fav Pokemon: " + pokeId.toString())
                }

            }
        }
    }
}

