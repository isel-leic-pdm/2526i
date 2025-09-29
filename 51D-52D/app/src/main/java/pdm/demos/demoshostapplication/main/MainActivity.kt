package pdm.demos.demoshostapplication.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pdm.demos.demoshostapplication.about.AboutActivity
import pdm.demos.demoshostapplication.crowdtally.CrowdTallyActivity
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

/**
 * The application's main activity. It's the host of the screen displayed when the application starts.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemosHostApplicationTheme {
                MainScreen(onNavigate = { handleNavigation(it) })
            }
        }
    }

    private fun handleNavigation(navigationEvent: TitleScreenNavigationIntent) {
        when (navigationEvent) {
            TitleScreenNavigationIntent.NavigateToAbout -> navigateToAbout()
            TitleScreenNavigationIntent.NavigateToCrowdTally -> navigateToCrowdTally()
        }
    }

    private fun navigateToCrowdTally() {
        val intent = Intent(this, CrowdTallyActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }
}
