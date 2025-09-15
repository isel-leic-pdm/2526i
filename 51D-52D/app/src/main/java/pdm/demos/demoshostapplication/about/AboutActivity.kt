package pdm.demos.demoshostapplication.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme
import androidx.core.net.toUri

class AboutActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemosHostApplicationTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    AboutScreen(
                        onNavigate = { handleNavigation(it) },
                        modifier = Modifier.padding(paddingValues = innerPadding)
                    )
                }
            }
        }
    }

    private fun handleNavigation(it: AboutScreenNavigationIntent) {
        when (it) {
            is AboutScreenNavigationIntent.YouTube -> navigateToURL(it.destination)
        }
    }

    private fun navigateToURL(destination: String) {
        val webpage: Uri = destination.toUri()
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(intent)
    }
}