package pdm.demos.demoshostapplication.login.domain

import kotlinx.coroutines.flow.Flow

/**
 * Data class representing authentication information.
 * @param userEmail The user's email.
 * @param authToken The authentication token.
 */
data class AuthInfo(val userEmail: String, val authToken: String)

/**
 * Repository interface for managing authentication information.
 */
interface AuthInfoRepo {

    /**
     * A Flow that emits the current authentication information, or null if none is stored.
     */
    val authInfo: Flow<AuthInfo?>

    /**
     * Saves the provided authentication information.
     * @param authInfo The authentication information to be saved.
     */
    suspend fun saveAuthInfo(authInfo: AuthInfo)

    /**
     * Retrieves the stored authentication information.
     * @return The stored authentication information, or null if none is stored.
     */
    suspend fun getAuthInfo(): AuthInfo?

    /**
     * Clears the stored authentication information.
     */
    suspend fun clearAuthInfo()
}

/**
 * A fake implementation of AuthInfoRepo for testing and development purposes.
 */
class FakeAuthInfoRepo : AuthInfoRepo {
    private var storedAuthInfo: AuthInfo? = null

    override val authInfo: Flow<AuthInfo?>
        get() = TODO("Not yet implemented")

    override suspend fun saveAuthInfo(authInfo: AuthInfo) {
        storedAuthInfo = authInfo
    }

    override suspend fun getAuthInfo(): AuthInfo? {
        return storedAuthInfo
    }

    override suspend fun clearAuthInfo() {
        storedAuthInfo = null
    }
}