package pdm.demos.demoshostapplication.crowdtally

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

class CrowdTallyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemosHostApplicationTheme {
                CrowdTallyScreen()
            }
        }
    }
}