package pdm.demos.demoshostapplication.pubsub.subscribe

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import pdm.demos.demoshostapplication.DependenciesContainer
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

class SubscribeActivity : ComponentActivity() {

    companion object {
        fun navigate(ctx: ComponentActivity) {
            val intent = Intent(ctx, SubscribeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            }
            ctx.startActivity(intent)
        }
    }

    private val viewModel: SubscribeScreenViewModel by viewModels {
        val diContainer = application as DependenciesContainer
        SubscribeScreenViewModel.getFactory(
            service = diContainer.messageBoardService
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemosHostApplicationTheme {
                SubscribeScreen(
                    viewModel = viewModel,
                    onBackNavigationIntent = { finish() }
                )
            }
        }
    }
}