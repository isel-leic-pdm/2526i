package pdm.demos.demoshostapplication.joke.reactive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pdm.demos.demoshostapplication.R
import pdm.demos.demoshostapplication.joke.common.domain.Joke
import pdm.demos.demoshostapplication.ui.TopBar
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme
import java.net.URL

@Composable
fun JokeView(joke: Joke, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = joke.text,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )
        Text(
            text = joke.source.host,
            style = MaterialTheme.typography.bodySmall,
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun JokeViewPreview() {
    DemosHostApplicationTheme {
        val aJoke = Joke(
            text = "It's a 5 minute walk from my house to the pub\n" +
                    "It's a 35 minute walk from the pub to my house\n" +
                    "The difference is staggering.",
            source = URL("https://www.keeplaughingforever.com/corny-dad-jokes")
        )
        JokeView(aJoke)
    }
}

@Composable
fun JokeScreenView(
    modifier: Modifier = Modifier,
    onBackNavigate: (() -> Unit)? = null,
    joke: Joke? = null,
    error: String? = null,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        topBar = {
            if (onBackNavigate != null) {
                TopBar(
                    title = stringResource(R.string.jokes_title),
                    onBackIntent = onBackNavigate
                )
            }
        },
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (joke != null) {
                    JokeView(joke = joke)
                }
            }
            if (error != null) {
                Text(
                    text = error,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun JokeScreenViewPreview() {
    DemosHostApplicationTheme {
        val aJoke = Joke(
            text = "It's a 5 minute walk from my house to the pub\n" +
                    "It's a 35 minute walk from the pub to my house\n" +
                    "The difference is staggering.",
            source = URL("https://www.keeplaughingforever.com/corny-dad-jokes")
        )

        JokeScreenView(
            onBackNavigate = { },
            joke = aJoke,
            error = "Something went wong while fetching the joke."
        )

    }
}