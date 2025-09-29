package pdm.demos.demoshostapplication.crowdtally

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pdm.demos.demoshostapplication.ui.RoundButton
import pdm.demos.demoshostapplication.ui.TopBar
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

@Composable
fun CrowdTallyScreen() {
    var counter by remember { mutableIntStateOf(value = 0) }

    Scaffold(topBar = { TopBar( ) }) { innerPadding ->
    }
}

@Preview
@Composable
fun CrowdTallyScreenPreview() {
    DemosHostApplicationTheme {
        CrowdTallyScreen()
    }
}