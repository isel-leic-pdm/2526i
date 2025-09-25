package pdm.demos.demoshostapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import pdm.demos.demoshostapplication.ui.theme.DemosHostApplicationTheme

const val BACK_BUTTON_TAG = "back_button"
const val INFO_BUTTON_TAG = "info_button"
const val TITLE_TEXT_TAG = "title_text"

/**
 * Composable function used to describe the top bar.
 *
 * @param title the title to be displayed in the top bar.
 * @param onBackIntent the handler function of the "back navigation" event. If null, the top bar
 * will not include a back button.
 * @param onInfoIntent the handler function of the "navigate to info" event. If null, the top bar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String = "",
    onBackIntent: (() -> Unit)? = null,
    onInfoIntent: (() -> Unit)? = null,
) {
    TopAppBar(
        title = { Text(text = title, modifier = Modifier.testTag(tag = TITLE_TEXT_TAG)) },
        navigationIcon = {
            onBackIntent?.let {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.clickable(onClick = it).testTag(tag = BACK_BUTTON_TAG)
                )
            }
        },
        actions = {
            onInfoIntent?.let { 
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "About",
                    modifier = Modifier.clickable(onClick = it).testTag(tag = INFO_BUTTON_TAG)
                )
            }
        }
    )
}

@Preview
@Composable
private fun TopBarWithBackNavigationPreview() {
    DemosHostApplicationTheme {
        TopBar(title = "Some App Title", onBackIntent = { }, onInfoIntent = { })
    }
}

@Preview
@Composable
private fun TopBarWithoutBackNavigationPreview() {
    DemosHostApplicationTheme {
        TopBar(title = "", onInfoIntent = { })
    }
}

@Preview
@Composable
private fun TopBarWithoutInfoNavigationPreview() {
    DemosHostApplicationTheme {
        TopBar(title = "", onBackIntent = { })
    }
}