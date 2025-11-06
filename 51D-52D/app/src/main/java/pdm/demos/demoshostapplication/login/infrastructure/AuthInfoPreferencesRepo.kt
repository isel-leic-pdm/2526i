package pdm.demos.demoshostapplication.login.infrastructure

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import pdm.demos.demoshostapplication.login.domain.AuthInfo
import pdm.demos.demoshostapplication.login.domain.AuthInfoRepo

/**
 * Implementation of AuthInfoRepo that uses SharedPreferences to store authentication information.
 */
class AuthInfoPreferencesRepo(private val store: DataStore<Preferences>) : AuthInfoRepo {

    private val userEmailKey: Preferences.Key<String> = stringPreferencesKey(name = "user_email")
    private val authTokenKey: Preferences.Key<String> = stringPreferencesKey(name = "auth_token")

    override val authInfo: Flow<AuthInfo?>
        get() = TODO("Not yet implemented")

    override suspend fun saveAuthInfo(authInfo: AuthInfo) {
        store.edit { preferences ->
            preferences[userEmailKey] = authInfo.userEmail
            preferences[authTokenKey] =  authInfo.authToken
        }
    }

    override suspend fun getAuthInfo(): AuthInfo? {
        val preferences: Preferences = store.data.last()
        return preferences.toAuthInfo()
    }

    override suspend fun clearAuthInfo() {
        store.edit { it.clear() }
    }

    fun Preferences.toAuthInfo(): AuthInfo? =
        this[userEmailKey]?.let {
            val email = it
            val token = this[authTokenKey] ?: return null
            AuthInfo(userEmail = email, authToken = token)
        }
}
