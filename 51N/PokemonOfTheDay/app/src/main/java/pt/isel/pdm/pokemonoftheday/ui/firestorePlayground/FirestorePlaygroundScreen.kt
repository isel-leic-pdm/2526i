package pt.isel.pdm.pokemonoftheday.ui.firestorePlayground

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import pt.isel.pdm.firebaseexplorer.screens.main.MessageView
import pt.isel.pdm.firebaseexplorer.screens.main.ModelListView
import pt.isel.pdm.pokemonoftheday.ui.common.CustomAppTopBar
import pt.isel.pdm.pokemonoftheday.R
import pt.isel.pdm.pokemonoftheday.domain.SimpleModel
import pt.isel.pdm.pokemonoftheday.ui.common.NavigationActions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirestorePlaygroundScreen(
    viewModel: FirestorePlaygroundViewModel,
    navigateBack: () -> Unit,
    onSimpleModelClicked: (SimpleModel) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.create()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        },
        topBar = {

            CustomAppTopBar(
                title = R.string.firestore_playground,
                navActions = NavigationActions(
                    onBackAction = navigateBack
                )
            )
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {


            if (viewModel.message.isNotEmpty())
                MessageView(viewModel.message)

            ModelListView(
                data = viewModel.modelList,
                onUpdate = { viewModel.update(it) },
                onDelete = { viewModel.delete(it) },
                onRefresh = { viewModel.getAll() },
                onClicked = onSimpleModelClicked
            )


        }


    }

    if (viewModel.error != null) {
        ErrorView(
            viewModel.error!!,
            onDismiss = { viewModel.dismissError() })
    }

    if (viewModel.loading) {
        LoadingView();
    }
}

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickable(
                indication = null, // disable ripple effect
                interactionSource = remember { MutableInteractionSource() },
                onClick = { }
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Loading")
    }
}


@Composable
fun ErrorView(
    error: Exception,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickable(
                indication = null, // disable animation
                interactionSource = remember { MutableInteractionSource() },
                onClick = { }
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Error")
        Text(error.toString())
        Button(onClick = onDismiss) {
            Text("Dismiss")
        }
    }
}