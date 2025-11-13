package pt.isel.pdm.pokemonoftheday.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pt.isel.pdm.pokemonoftheday.domain.database.dao.PokemonHistoryDao
import pt.isel.pdm.pokemonoftheday.domain.database.entities.User
import pt.isel.pdm.pokemonoftheday.domain.database.dao.UserDao
import pt.isel.pdm.pokemonoftheday.domain.database.entities.PokemonDataEntity

@Database(version = 1, entities = [User::class, PokemonDataEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun pokemonHistoryDao(): PokemonHistoryDao
}


