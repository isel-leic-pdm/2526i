package pdm.demos.demoshostapplication.about

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import pdm.demos.demoshostapplication.R
import pdm.demos.demoshostapplication.about.AboutScreenNavigationIntent.Back
import pdm.demos.demoshostapplication.ui.TopBar
import kotlin.math.min

/**
 * Represents the possible navigation destinations of the about screen.
 *
 * DESIGN NOTE:
 * In this case we are using a sealed hierarchy instead of using a enum class. In this case we
 * opted for this approach to show how we can pass information along with the event (e.g. the
 * YouTube channel URL)
 */
sealed class AboutScreenNavigationIntent {
    class OpenUrl(val destination: Uri) : AboutScreenNavigationIntent()
    class SendEmail(val to: String, val subject: String) : AboutScreenNavigationIntent()
    object Back : AboutScreenNavigationIntent()
}

private const val AUTHOR_EMAIL = "paulo.pereira@isel.pt"
private const val EMAIL_SUBJECT = "About the Dice Roller App"

/**
 * Root composable for the about screen, the one that displays information about the app.
 * @param onNavigate the callback to be invoked when the user intends to navigate to other screens
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(onNavigate: (AboutScreenNavigationIntent) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(onBackIntent = { onNavigate(Back) }) }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues = innerPadding)
                .fillMaxSize(),
        ) {
            Author(onSendEmailRequested = {
                onNavigate(AboutScreenNavigationIntent.SendEmail(to = AUTHOR_EMAIL, subject = EMAIL_SUBJECT))
            })
            Socials(onOpenUrlRequested = {
                onNavigate(AboutScreenNavigationIntent.OpenUrl(destination = it))
            })
        }
    }
}

/**
 * Composable used to display information about the author of the application
 */
@Composable
private fun Author(onSendEmailRequested: () -> Unit = { }) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onSendEmailRequested() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_author),
            contentDescription = null,
            modifier = Modifier.sizeIn(
                minWidth = 100.dp, maxWidth = 200.dp,
                minHeight = 100.dp, maxHeight = 200.dp
            )
        )
        Text(text = "Paulo Pereira", style = MaterialTheme.typography.titleLarge)
        Icon(imageVector = Icons.Default.Email, contentDescription = null)
    }
}

/**
 * Used to represent information about a social network in the about screen
 * @param link the link to the social network
 * @param imageId the id of the image to be displayed
 */
private data class SocialInfo(val link: Uri, @DrawableRes val imageId: Int)

private val socials = listOf(
    SocialInfo(
        link = "https://www.linkedin.com/in/palbp/".toUri(),
        imageId = R.drawable.ic_linkedin
    ),
    SocialInfo(
        link = "https://www.twitch.tv/paulo_pereira".toUri(),
        imageId = R.drawable.ic_twitch
    ),
    SocialInfo(
        link = "https://www.youtube.com/@ProfPauloPereira".toUri(),
        imageId = R.drawable.ic_youtube
    ),
    SocialInfo(
        link = "https://discord.com/users/387733375064080396".toUri(),
        imageId = R.drawable.ic_discord
    ),
    SocialInfo(
        link = "https://palbp.github.io/".toUri(),
        imageId = R.drawable.ic_home
    )
)

@Composable
private fun Socials(
    onOpenUrlRequested: (Uri) -> Unit = { },
) {
    val countPerRow = 4
    repeat(times = socials.size / countPerRow + 1) { count ->
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            val start = count * countPerRow
            val end = min(a = (count + 1) * countPerRow, b = socials.size)
            socials.subList(fromIndex = start, toIndex = end).forEach {
                Social(id = it.imageId, onClick = { onOpenUrlRequested(it.link) })
            }
        }
    }
}

@Composable
private fun Social(@DrawableRes id: Int, onClick: () -> Unit) {
    Image(
        painter = painterResource(id = id),
        contentDescription = null,
        modifier = Modifier
            .sizeIn(maxWidth = 60.dp)
            .clickable(onClick = onClick)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun InfoScreenPreview() {
    AboutScreen(onNavigate = { })
}