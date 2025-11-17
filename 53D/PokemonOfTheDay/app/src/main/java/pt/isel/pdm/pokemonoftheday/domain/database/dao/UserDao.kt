package pt.isel.pdm.pokemonoftheday.domain.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pt.isel.pdm.pokemonoftheday.domain.database.entities.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM users ORDER BY name ASC")
    fun getAllUsers(): Flow<List<User>>

    @Query("SELECT * FROM users ORDER BY name DESC LIMIT :howMany")
    fun getOldestUsers(howMany: Int = 10): Flow<List<User>>

    @Delete
    suspend fun delete(user: User)

    @Update
    suspend fun update(user: User)
}