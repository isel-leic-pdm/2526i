package pt.isel.pdm.pokemonoftheday.domain.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import pt.isel.pdm.pokemonoftheday.domain.PokemonData


@Entity(tableName = "FavsPokemonHistory")
data class PokemonDataEntity(
    @PrimaryKey()
    val id: Int,
    val name: String,
    val imageUrl: String,
    val height: Int, // decimeter
    val weight: Int  // hectograms
) {

}

fun PokemonData.toEntity(): PokemonDataEntity {
    return PokemonDataEntity(id, name, imageUrl, height, weight)
}

fun PokemonDataEntity.toPokemonData(): PokemonData {
    return PokemonData(id, name, imageUrl, height, weight)
}