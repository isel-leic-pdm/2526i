package pdm.demos.demoshostapplication.login.domain

import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 * Interface representing the login service.
 */
interface LoginService {

    /**
     * Logs in a user with the provided credentials.
     * @param credentials The user's credentials.
     * @return A string representing the authentication token to be used upon successful login.
     * @throws InvalidCredentialsException if the login fails.
     */
    suspend fun login(credentials: UserCredentials): String
}

/**
 * Exception thrown when user credentials are invalid.
 */
class InvalidCredentialsException : Exception("Invalid user credentials provided")

/**
 * A fake implementation of the LoginService for testing purposes.
 */
class FakeLoginService : LoginService {
    override suspend fun login(credentials: UserCredentials): String {
        delay(timeMillis = Random.nextLong(from = 3000, until = 5000))
        if (credentials.email.lowercase() != "paulo.pereira@isel.pt" )
            throw InvalidCredentialsException()
        return "fake_auth_token_123456"
    }
}

