package pt.isel.pdm.pokemonoftheday.services

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pt.isel.pdm.pokemonoftheday.domain.PokemonData

class FakePokemonFavouriteHistoryService : PokemonFavouriteHistoryService {
    override suspend fun getHistory(): Flow<List<PokemonData>> {
        return flow { emptyList<PokemonData>() }
    }

    override suspend fun add(p: PokemonData) {
    }

    override suspend fun remove(p: PokemonData) {
    }
}