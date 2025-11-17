package pt.isel.pdm.pokemonoftheday.domain.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pt.isel.pdm.pokemonoftheday.domain.database.entities.PokemonDataEntity

@Dao
interface PokemonDataEntityDao {
    @Insert
    suspend fun insert(p: PokemonDataEntity)

    @Delete
    suspend fun delete(p: PokemonDataEntity)

    @Query("SELECT * FROM FavsPokemonHistory")
    fun getAll(): Flow<List<PokemonDataEntity>>

}