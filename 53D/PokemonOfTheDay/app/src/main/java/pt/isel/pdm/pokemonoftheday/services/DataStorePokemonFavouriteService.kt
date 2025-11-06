package pt.isel.pdm.pokemonoftheday.services

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStorePokemonFavouriteService(
    private val dataStore: DataStore<Preferences>
) : PokemonFavouriteService {
    val favouriteKey = intPreferencesKey("favPokemon")


    override val favourite: Flow<Int?> = dataStore.data.map {
        it[favouriteKey]
    }

    override suspend fun setFavourite(id: Int) {
        dataStore.edit {
            it[favouriteKey] = id
        }
    }

    override suspend fun clearFavourite() {
        dataStore.edit {
            it.remove(favouriteKey)
        }
    }


}

