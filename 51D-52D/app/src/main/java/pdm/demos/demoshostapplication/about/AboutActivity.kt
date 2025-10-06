package pdm.demos.demoshostapplication.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.net.toUri
import pdm.demos.demoshostapplication.about.AboutScreenNavigationIntent.Back
import pdm.demos.demoshostapplication.about.AboutScreenNavigationIntent.OpenUrl
import pdm.demos.demoshostapplication.about.AboutScreenNavigationIntent.SendEmail
import pdm.demos.demoshostapplication.crowdtally.CrowdTallyActivity
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

/**
 * The activity that hosts the about screen.
 */
class AboutActivity : ComponentActivity() {

    companion object {
        fun navigate(ctx: ComponentActivity) {
            val intent = Intent(ctx, AboutActivity::class.java)
            ctx.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemosHostApplicationTheme {
                AboutScreen(onNavigate = { handleNavigation(it) })
            }
        }
    }

    private fun handleNavigation(it: AboutScreenNavigationIntent) {
        when (it) {
            is OpenUrl -> navigateToURL(destination = it.destination.toString())
            is SendEmail -> navigateToEmail(to = it.to, subject = it.subject)
            Back -> finish()
        }
    }

    private fun navigateToURL(destination: String) {
        val webpage: Uri = destination.toUri()
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun navigateToEmail(to: String, subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = "mailto:".toUri()
            putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}
