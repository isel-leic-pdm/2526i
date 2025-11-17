package pt.isel.pdm.pokemonoftheday.services

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pt.isel.pdm.pokemonoftheday.domain.PokemonData
import pt.isel.pdm.pokemonoftheday.domain.database.AppDatabase
import pt.isel.pdm.pokemonoftheday.domain.database.dao.PokemonDataEntityDao
import pt.isel.pdm.pokemonoftheday.domain.database.entities.toEntity
import pt.isel.pdm.pokemonoftheday.domain.database.entities.toPokemonData

class RoomPokemonFavouriteHistoryService(
    private val dao: PokemonDataEntityDao
) :
    PokemonFavouriteHistoryService {
    override suspend fun add(p: PokemonData) {
        dao.insert(p.toEntity())
    }

    override suspend fun getAllHistory(): Flow<List<PokemonData>> {
        return dao.getAll().map { list -> list.map { it.toPokemonData() } }
    }

    override suspend fun delete(p: PokemonData) {
        dao.delete(p.toEntity())
    }
}