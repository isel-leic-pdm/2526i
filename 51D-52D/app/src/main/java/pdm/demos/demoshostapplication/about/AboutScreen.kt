package pdm.demos.demoshostapplication.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

sealed class AboutScreenNavigationIntent {
    class YouTube(val destination: String) : AboutScreenNavigationIntent()
}

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onNavigate: (AboutScreenNavigationIntent) -> Unit = { }
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Button(onClick = { onNavigate(
            AboutScreenNavigationIntent.YouTube(destination = "https://www.youtube.com/@ProfPauloPereira")
        ) }) {
            Text(text = "YouTube")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen()
}
