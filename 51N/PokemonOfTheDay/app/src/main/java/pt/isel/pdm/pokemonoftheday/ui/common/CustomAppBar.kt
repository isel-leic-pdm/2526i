package pt.isel.pdm.pokemonoftheday.ui.common

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import pt.isel.pdm.pokemonoftheday.R

class NavigationActions(
    val onBackAction: (() -> Unit)? = null,
    val onAboutAction: (() -> Unit)? = null,
    val onFirestorePlayground : (() -> Unit)? = null,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppTopBar(
    @StringRes title: Int? = null,
    navActions: NavigationActions = NavigationActions()
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(title ?: R.string.app_name),
                modifier = Modifier.testTag(CustomAppTopBarTestTags.TITLE_TEXT)

            )
        },
        navigationIcon = {
            ConditionalIconButton(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                action = navActions.onBackAction,
                modifier = Modifier.testTag(CustomAppTopBarTestTags.BACK_BUTTON)
            )
        },
        actions = {
            ConditionalIconButton(
                icon = Icons.Default.Info,
                action = navActions.onAboutAction,
                modifier = Modifier.testTag(CustomAppTopBarTestTags.ABOUT_BUTTON)
            )
            ConditionalIconButton(
                icon = Icons.Default.Warning,
                action = navActions.onFirestorePlayground,
            )

        }
    )
}

@Composable
fun ConditionalIconButton(
    icon: ImageVector,
    action: (() -> Unit)?,
    modifier: Modifier = Modifier

) {
    if (action != null) {
        IconButton(
            onClick = action,
            modifier = modifier
        ) {
            Icon(imageVector = icon, contentDescription = "")
        }
    }
}

object CustomAppTopBarTestTags {

    const val ABOUT_BUTTON = "ABOUT_BUTTON"
    const val BACK_BUTTON = "BACK_BUTTON"
    const val TITLE_TEXT = "TITLE_TEXT"
}
