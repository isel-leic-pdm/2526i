package pt.isel.pdm.pokemonoftheday.ui.roomPlayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import pt.isel.pdm.pokemonoftheday.PokemonOfTheDayApplication
import pt.isel.pdm.pokemonoftheday.R
import pt.isel.pdm.pokemonoftheday.ui.common.CustomAppTopBar
import pt.isel.pdm.pokemonoftheday.ui.common.NavigationActions
import pt.isel.pdm.pokemonoftheday.ui.common.viewModelInit
import pt.isel.pdm.pokemonoftheday.ui.theme.PokemonOfTheDayTheme
import kotlin.getValue

class RoomPlaygroundActivity : ComponentActivity() {
    val viewModel by viewModels<RoomPlaygroundViewModel> {
        viewModelInit {
            RoomPlaygroundViewModel((application as PokemonOfTheDayApplication).appDatabase)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RoomPlaygroundScreen(
                viewModel,
                { finish() }
            )
        }
    }
}

@Composable
fun RoomPlaygroundScreen(
    viewModel: RoomPlaygroundViewModel,
    onBack: () -> Unit
) {
    PokemonOfTheDayTheme {
        val users = viewModel.users.collectAsState().value
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    viewModel.createUser()
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "")
                }
            },
            topBar = {
                CustomAppTopBar(
                    title = R.string.room_playground,
                    navActions = NavigationActions(
                        onBackAction = onBack
                    )
                )
            }

        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                items(users) {
                    Column {
                        Text(text = "${it.id} - ${it.name}")
                        Button(onClick = {
                            viewModel.deleteUser(it)
                        }) {
                            Text(stringResource(R.string.delete))
                        }
                    }
                }
            }
        }
    }
}