package pt.isel.pdm.pokemonoftheday.services

import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePokemonFavouriteService(
    private val dataStore: DataStore<Preferences>
) : PokemonFavouriteService {

    val POKEMON_ID = intPreferencesKey("pokemonId")

    override val currentFavourite: Flow<Int?>
        get() = dataStore.data.map {
            it[POKEMON_ID]
        }

    override suspend fun set(p: Int) {
        dataStore.edit {
            it[POKEMON_ID] = p
        }
    }

    override suspend fun clear() {
        dataStore.edit {
            it.remove(POKEMON_ID)
        }
    }

}