package pdm.demos.demoshostapplication.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pdm.demos.demoshostapplication.about.AboutActivity
import pdm.demos.demoshostapplication.crowdtally.CrowdTallyActivity
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

const val TAG = "PDM_DEMO"

/**
 * The application's main activity. It's the host of the screen displayed when the application starts.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.v(TAG, "MainActivity: onCreate called")
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
        val intent = Intent(this, CrowdTallyActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
        }
        startActivity(intent)
    }

    private fun navigateToAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        Log.v(TAG, "MainActivity: onStart called")
    }

    override fun onStop() {
        super.onStop()
        Log.v(TAG, "MainActivity: onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "MainActivity: onDestroy called")
    }
}
