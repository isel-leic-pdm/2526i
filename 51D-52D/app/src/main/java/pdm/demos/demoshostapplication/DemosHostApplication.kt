package pdm.demos.demoshostapplication

import android.app.Application
import pdm.demos.demoshostapplication.joke.domain.FakeJokesService
import pdm.demos.demoshostapplication.login.domain.FakeLoginService

const val JOKE_APP_TAG = "JokeApp"
const val LOGIN_APP_TAG = "LoginApp"

class DemosHostApplication : Application() {

    val jokeService by lazy { FakeJokesService() }
    val loginService by lazy { FakeLoginService() }
}