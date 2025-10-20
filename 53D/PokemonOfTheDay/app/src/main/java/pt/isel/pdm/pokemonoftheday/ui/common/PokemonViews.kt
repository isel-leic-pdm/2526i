package pt.isel.pdm.pokemonoftheday.ui.common

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pt.isel.pdm.pokemonoftheday.domain.PokemonData
import pt.isel.pdm.pokemonoftheday.domain.heightInMeter
import pt.isel.pdm.pokemonoftheday.domain.weightInKilogram
import pt.isel.pdm.pokemonoftheday.services.Pokemons


@Composable
fun FullScreenPokemonDataView(p: PokemonData) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag(PokemonViewsTestTags.FSPD_VIEW_TAG),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = p.id.toString(),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.testTag(PokemonViewsTestTags.FSPD_ID_TAG)

        )
        //requires  implementation("io.coil-kt.coil3:coil-compose:3.2.0")
        AsyncImage(
            model = p.imageUrl,
            contentDescription = "pokemon image",
            modifier = Modifier
                .size(200.dp)
                .testTag(PokemonViewsTestTags.FSPD_IMAGE_TAG)
        )

        Text(
            text = p.name,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.testTag(PokemonViewsTestTags.FSPD_NAME_TAG)
        )

        Text(
            text = "Height:%.2f m".format(p.heightInMeter()),
            modifier = Modifier.testTag(PokemonViewsTestTags.FSPD_HEIGHT_TAG)
        )

        Text(
            text = "Weight:%.2f kg".format(p.weightInKilogram()),
            modifier = Modifier.testTag(PokemonViewsTestTags.FSPD_WEIGHT_TAG)
        )
    }
}

@Preview
@Composable()
private fun FullScreenPokemonDataViewPreview() {
    FullScreenPokemonDataView(Pokemons.List[0])
}


object PokemonViewsTestTags {
    const val FSPD_VIEW_TAG = "FSPD_VIEW_TAG"
    const val FSPD_ID_TAG = "FSPD_ID_TAG"
    const val FSPD_HEIGHT_TAG = "FSPD_HEIGHT_TAG"
    const val FSPD_WEIGHT_TAG = "FSPD_WEIGHT_TAG"
    const val FSPD_IMAGE_TAG = "FSPD_IMAGE_TAG"
    const val FSPD_NAME_TAG = "FSPD_NAME_TAG"
}
