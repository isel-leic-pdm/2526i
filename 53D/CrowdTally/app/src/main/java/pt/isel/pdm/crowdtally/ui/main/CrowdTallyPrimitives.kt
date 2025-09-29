package pt.isel.pdm.crowdtally.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ArrowButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean = true
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "arrow"
        )
    }
}