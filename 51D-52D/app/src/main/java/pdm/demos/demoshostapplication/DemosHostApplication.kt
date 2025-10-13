package pdm.demos.demoshostapplication

import android.app.Application
import pdm.demos.demoshostapplication.joke.domain.FakeJokesService

const val JOKE_APP_TAG = "JokeApp"

class DemosHostApplication : Application() {

    val jokeService by lazy { FakeJokesService() }
}