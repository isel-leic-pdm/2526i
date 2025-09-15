package pt.isel.pdm.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.isel.pdm.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {


    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart")
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate")
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {

                        Post(
                            title = "Um titulo",
                            description = "uma descricao",
                            stars = 3,
                            hearts = 13,
                            modifier = Modifier.padding(innerPadding)
                        )

                        Button(onClick = { NavigateToA() }) {
                            Text("Navigate to A")
                        }
                        Button(onClick = { NavigateToUri() })
                        {
                            Text("Navigate to uri")
                        }
                    }

                }
            }
        }
    }

    fun NavigateToA() {
        val intent = Intent(this, A::class.java)
        startActivity(intent)

    }

    fun NavigateToUri() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sapo.pt"))

        startActivity(intent)
    }
}

@Composable
fun Post(
    title: String,
    description: String,
    stars: Int,
    hearts: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = title)
        Text(text = description)

        Row {
            Reaction(stars, "stars", Icons.Default.Star)
            Reaction(hearts, "hearts", Icons.Default.Favorite)
        }
    }
}

@Composable
fun Reaction(count: Int, label: String, image: ImageVector) {
    Box {
        Icon(
            imageVector = image,
            contentDescription = label,
            modifier = Modifier
                .align(Alignment.Center)
                .size(30.dp)
        )
        Text(
            text = count.toString(),
            color = Color.Cyan,
            modifier = Modifier.align(Alignment.Center),
            fontSize = 10.sp
        )
    }
}

@Preview
@Composable
fun ReactionPreview() {
    Reaction(13, "hearts", Icons.Default.Favorite)
}

@Preview
@Composable
fun PostPreview() {
    Post(
        title = "Um titulo",
        description = "uma descricao",
        stars = 3,
        hearts = 13,
    )
}