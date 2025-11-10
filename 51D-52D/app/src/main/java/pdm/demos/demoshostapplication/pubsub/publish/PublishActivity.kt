package pdm.demos.demoshostapplication.pubsub.publish

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import pdm.demos.demoshostapplication.DependenciesContainer
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme
import kotlin.getValue

class PublishActivity : ComponentActivity() {

    companion object {
        fun navigate(ctx: ComponentActivity) {
            val intent = Intent(ctx, PublishActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            }
            ctx.startActivity(intent)
        }
    }

    private val viewModel: PublishScreenViewModel by viewModels {
        val diContainer = application as DependenciesContainer
        PublishScreenViewModel.getFactory(
            service = diContainer.messageBoardService
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemosHostApplicationTheme {
                PublishScreen(
                    viewModel = viewModel,
                    onBackNavigationIntent = { finish() }
                )
            }
        }
    }
}