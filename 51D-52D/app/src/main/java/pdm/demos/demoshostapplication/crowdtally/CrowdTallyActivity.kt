package pdm.demos.demoshostapplication.crowdtally

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import pdm.demos.demoshostapplication.crowdtally.domain.CrowdTallyViewModel
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

/**
 * Activity that hosts the Crowd Tally Demo. The demo consists of a single screen.
 */
class CrowdTallyActivity : ComponentActivity() {

    private val viewModel by viewModels<CrowdTallyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemosHostApplicationTheme {
                CrowdTallyScreen(
                    viewModel = viewModel,
                    onBackNavigationIntent = { finish() }
                )
            }
        }
    }
}