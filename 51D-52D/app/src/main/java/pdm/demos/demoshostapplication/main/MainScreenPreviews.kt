package pdm.demos.demoshostapplication.main

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

@PreviewFontScale
@Composable
private fun MainScreenPreviewVariableFonts() {
    DemosHostApplicationTheme {
        MainScreen(
            modifier = Modifier.padding(all = 50.dp)
        )
    }
}

@PreviewScreenSizes
@Composable
private fun MainScreenPreviewScreenSizes() {
    DemosHostApplicationTheme {
        MainScreen(
            modifier = Modifier.padding(all = 50.dp)
        )
    }
}
