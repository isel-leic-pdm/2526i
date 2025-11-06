package pdm.demos.demoshostapplication.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import pdm.demos.demoshostapplication.DependenciesContainer

/**
 * Activity representing the Login Screen demo.
 */
class LoginActivity : ComponentActivity() {

    companion object {
        fun navigate(ctx: ComponentActivity) {
            val intent = Intent(ctx, LoginActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            }
            ctx.startActivity(intent)
        }
    }

    private val viewModel: LoginScreenViewModel by viewModels {
        val diContainer = application as DependenciesContainer
        LoginScreenViewModel.getFactory(
            service = diContainer.loginService,
            repo = diContainer.authInfoRepo
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen(
                viewModel = viewModel,
                onBackNavigationIntent = { finish() }
            )
        }
    }
}
