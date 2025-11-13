package pt.isel.pdm.pokemonoftheday.domain.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PokemonHistory")
class PokemonDataEntity(
    @PrimaryKey()
    val id: Int,
    val name: String,
    val imageUrl: String,
    val height: Int, // decimeter
    val weight: Int  // hectograms
)