package pt.isel.pdm.crowdtally.ui.main

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pt.isel.pdm.crowdtally.ui.theme.CrowdTallyTheme

class MainActivity : ComponentActivity() {


    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart");
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop");
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause");
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume");
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy");
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate");
        val viewModel by viewModels<CrowdTallyViewModel>()
        enableEdgeToEdge()
        setContent {
            CrowdTallyScreen(viewModel)
        }
    }
}

