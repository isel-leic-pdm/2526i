package pdm.demos.demoshostapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class TitleScreenNavigationIntent {
    NavigateToAbout
}

@Composable
fun TitleScreen(
        modifier: Modifier = Modifier,
        onNavigate: (TitleScreenNavigationIntent) -> Unit = { }
    ) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Button(onClick = { onNavigate(TitleScreenNavigationIntent.NavigateToAbout) }) {
            Text("About")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TitleScreenPreview() {
    TitleScreen(
        modifier = Modifier.padding(50.dp)
    )
}
