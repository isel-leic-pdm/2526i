package pt.isel.pdm.pokemonoftheday.ui.about

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import pt.isel.pdm.pokemonoftheday.ui.theme.PokemonOfTheDayTheme
import java.util.Locale

class AboutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Activity", "About")
        enableEdgeToEdge()
        setContent {
            AboutScreen(
                { finish() }
            )
        }
    }

    companion object {
        fun navigate(ctx: Activity) {
            val intent = Intent(ctx, AboutActivity::class.java)
            ctx.startActivity(intent)
        }
    }




}

