package pt.isel.pdm.pokemonoftheday.domain.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "megaid")
    val id: Int = 0,
    val name: String,
    val age: Int
)