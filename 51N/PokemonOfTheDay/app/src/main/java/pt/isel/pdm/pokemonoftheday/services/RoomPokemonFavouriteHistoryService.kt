package pt.isel.pdm.pokemonoftheday.services

import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pt.isel.pdm.pokemonoftheday.domain.PokemonData
import pt.isel.pdm.pokemonoftheday.domain.database.AppDatabase
import pt.isel.pdm.pokemonoftheday.domain.database.dao.PokemonHistoryDao
import pt.isel.pdm.pokemonoftheday.domain.database.entities.PokemonDataEntity

class RoomPokemonFavouriteHistoryService(
    private val db: PokemonHistoryDao //please add a repo, im being lazy
) : PokemonFavouriteHistoryService {
    override suspend fun getHistory(): Flow<List<PokemonData>> {
        return db.getHistory().map { it.map { it.toPokemonData() } }
    }


    override suspend fun add(p: PokemonData) {
        try {
            db.insert(p.toPokemonDataEntity())
        } catch (e: Exception) {
            //to lazy to deal with same id inserts
        }
    }

    override suspend fun remove(p: PokemonData) {
        db.delete(p.toPokemonDataEntity())
    }
}

fun PokemonDataEntity.toPokemonData(): PokemonData =
    PokemonData(id, name, imageUrl, height, weight)

fun PokemonData.toPokemonDataEntity(): PokemonDataEntity =
    PokemonDataEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        height = height,
        weight = weight
    )