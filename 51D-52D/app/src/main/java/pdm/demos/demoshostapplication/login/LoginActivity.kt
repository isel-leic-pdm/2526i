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
        LoginScreenViewModel.getFactory(
            service = (application as DependenciesContainer).loginService
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
