package pt.isel.pdm.pokemonoftheday.domain.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pt.isel.pdm.pokemonoftheday.domain.database.entities.PokemonDataEntity

@Dao
interface PokemonHistoryDao {
    @Insert
    suspend fun insert(p: PokemonDataEntity)

    @Delete
    suspend fun delete(p: PokemonDataEntity)

    @Query("SELECT * FROM PokemonHistory")
    fun getHistory(): Flow<List<PokemonDataEntity>>

}