package pt.isel.pdm.firstapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import pt.isel.pdm.firstapp.ui.theme.FirstAppTheme

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
        enableEdgeToEdge()
        setContent {
            FirstAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                )
                { innerPadding ->
                    Column {
                        Post(
                            title = "a title",
                            description = "a description\nwith\n4\nlines",
                            stars = 2,
                            hearts = 88,
                            modifier = Modifier.padding(innerPadding)
                        )

                        ButtonToNavigate({
                            val intent = Intent(this@MainActivity, Activity2::class.java)
                            startActivity(intent)
                        })

                        Button(onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.sapo.pt"))
                            startActivity(intent)
                        })
                        {
                            Text("Open Sapo.pt")
                        }


                    }
                }
            }
        }
    }


}

@Composable
fun ButtonToNavigate(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("Navigate to Activity2")
    }
}

@Composable
fun ButtonToNavigate_DONOTUSE(activity: Activity) {
    Button(onClick = {
        val intent = Intent(activity, Activity2::class.java)
        activity.startActivity(intent)
    }) {
        Text("Navigate to Activity2")
    }
}

@Composable
fun ScaffoldArgument(innerPadding: PaddingValues) {
    Post(
        title = "a title",
        description = "a description\nwith\n4\nlines",
        stars = 2,
        hearts = 88,
        modifier = Modifier.padding(innerPadding)
    )
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
        Text(
            text = title,
            fontSize = 12.sp
        )
        Text(text = description)
        Row {
            Reaction(stars, Icons.Default.Star)
            Reaction(hearts, Icons.Default.Favorite)
        }
        Button(onClick = {
            Log.d("MainActivity", "Button pressed")
        })
        {
            Text("Click me")
        }
    }
}

@Composable
fun Reaction(counter: Int, image: ImageVector) {
    Box {
        Icon(
            imageVector = image,
            contentDescription = "",
            modifier = Modifier
                .size(40.dp),
            tint = Color.Blue
        )

        Text(
            text = counter.toString(),
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun TestPost() {
    Post(
        title = "a title",
        description = "a description\nwith\n4\nlines",
        stars = 2,
        hearts = 88,
        modifier = Modifier.padding(12.dp)
    )
}