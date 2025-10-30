package pt.isel.pdm.pokemonoftheday.services

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreStorageService(
    val prefs: DataStore<Preferences>
) : StorageService {

    val FAVS_KEY = intPreferencesKey("favs_key")

    override val favourite: Flow<Int?>
        get() = prefs.data.map { p ->
            p[FAVS_KEY]
        }

    override suspend fun setFavourite(pokemon: Int) {
        prefs.edit {
            it[FAVS_KEY] = pokemon
        }
    }

    override suspend fun removeFavourite() {
        prefs.edit {
            it.remove(FAVS_KEY)
        }
    }
}