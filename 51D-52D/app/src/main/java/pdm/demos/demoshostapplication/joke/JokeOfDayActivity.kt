package pdm.demos.demoshostapplication.joke

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import pdm.demos.demoshostapplication.DemosHostApplication


class JokeOfDayActivity : ComponentActivity() {

    companion object {
        fun navigate(ctx: ComponentActivity) {
            val intent = Intent(ctx, JokeOfDayActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            }
            ctx.startActivity(intent)
        }
    }

    private val viewModel: JokeOfDayScreenViewModel by viewModels {
        JokeOfDayScreenViewModel.getFactory(
            service = (application as DemosHostApplication).jokeService
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokeOfDayScreen(
                viewModel = viewModel,
                onBackIntent = { finish() }
            )
        }
    }
}
