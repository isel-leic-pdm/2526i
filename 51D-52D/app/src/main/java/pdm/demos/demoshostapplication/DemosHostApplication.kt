package pdm.demos.demoshostapplication

import android.app.Application
import pdm.demos.demoshostapplication.joke.domain.FakeJokeOfDayService

class DemosHostApplication : Application() {

    val jokeService by lazy { FakeJokeOfDayService() }
}