package pdm.demos.demoshostapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import pdm.demos.demoshostapplication.about.AboutActivity
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DemosHostApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TitleScreen(
                        onNavigate = {
                            navigateToAbout()
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun navigateToAbout() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }
}
