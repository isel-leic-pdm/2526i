package pt.isel.pdm.pokemonoftheday.domain

import androidx.room.Entity

data class PokemonData(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val height: Int, // decimeter
    val weight: Int  // hectograms
) {
    companion object {
        val None = PokemonData(-1, "none", "", -1, -2)
    }
}


fun PokemonData.heightInMeter(): Float = height / 10f
fun PokemonData.weightInKilogram(): Float = weight / 10f

