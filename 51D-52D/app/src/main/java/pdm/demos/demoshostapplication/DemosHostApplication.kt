package pdm.demos.demoshostapplication

import android.app.Application
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import pdm.demos.demoshostapplication.joke.domain.FakeJokesService
import pdm.demos.demoshostapplication.joke.domain.JokesService
import pdm.demos.demoshostapplication.joke.http.IcanhazDadJokes
import pdm.demos.demoshostapplication.login.domain.FakeLoginService
import pdm.demos.demoshostapplication.login.domain.LoginService

const val JOKE_APP_TAG = "JokeApp"
const val LOGIN_APP_TAG = "LoginApp"

/**
 * Container interface for dependencies used across the application.
 */
interface DependenciesContainer {
    val jokeService: JokesService
    val loginService: LoginService
}

/**
 * The Application class for the Demos Host Application.
 * It initializes and provides access to shared services used across the app.
 */
class DemosHostApplication : DependenciesContainer, Application() {

    private val httpClient by lazy {
        // HTTP engine is chosen by Ktor based on the current dependencies and platform.
        HttpClient {
            install(plugin = ContentNegotiation) {
                json(
                    json = Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    override val jokeService by lazy {
        IcanhazDadJokes(client = httpClient)
        //FakeJokesService()
    }

    override val loginService by lazy { FakeLoginService() }
}