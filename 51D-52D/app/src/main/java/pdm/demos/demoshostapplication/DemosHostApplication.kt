package pdm.demos.demoshostapplication

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import pdm.demos.demoshostapplication.joke.common.domain.JokesService
import pdm.demos.demoshostapplication.joke.common.http.IcanhazDadJokes
import pdm.demos.demoshostapplication.login.domain.AuthInfoRepo
import pdm.demos.demoshostapplication.login.domain.FakeLoginService
import pdm.demos.demoshostapplication.login.domain.LoginService
import pdm.demos.demoshostapplication.login.infrastructure.AuthInfoPreferencesRepo
import pdm.demos.demoshostapplication.pubsub.common.MessageBoardService
import pdm.demos.demoshostapplication.pubsub.common.MessageBoardFirestore

const val JOKE_APP_TAG = "JokeApp"

/**
 * Container interface for dependencies used across the application.
 */
interface DependenciesContainer {
    /**
     * The service to fetch jokes.
     */
    val jokeService: JokesService

    /**
     * The service to handle user login.
     */
    val loginService: LoginService

    /**
     * The repository to manage authentication information.
     */
    val authInfoRepo: AuthInfoRepo

    /**
     * The service to handle message board publishing and subscribing.
     */
    val messageBoardService: MessageBoardService
}

/**
 * The Application class for the Demos Host Application.
 * It initializes and provides access to shared services used across the app.
 */
class DemosHostApplication : DependenciesContainer, Application() {

    /**
     * The Ktor HTTP client used for network operations.
     */
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

    /**
     * The DataStore instance for storing authentication information.
     */
    private val dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_info")

    override val jokeService by lazy {
        IcanhazDadJokes(client = httpClient)
    }

    override val loginService by lazy { FakeLoginService() }

    override val authInfoRepo: AuthInfoRepo by lazy {
        AuthInfoPreferencesRepo(store = dataStore)
    }

    override val messageBoardService: MessageBoardService by lazy {
        MessageBoardFirestore(firestoreDb = Firebase.firestore)
    }
}