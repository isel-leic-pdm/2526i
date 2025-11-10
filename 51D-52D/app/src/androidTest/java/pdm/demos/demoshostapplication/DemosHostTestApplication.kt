package pdm.demos.demoshostapplication

import android.app.Application
import pdm.demos.demoshostapplication.joke.common.domain.FakeJokesService
import pdm.demos.demoshostapplication.login.domain.FakeAuthInfoRepo
import pdm.demos.demoshostapplication.login.domain.FakeLoginService
import pdm.demos.demoshostapplication.pubsub.common.FakeMessageBoardService


/**
 * The Application class to be used on the instrumented tests.
 */
class DemosHostTestApplication : DependenciesContainer, Application() {
    override val jokeService by lazy { FakeJokesService() }
    override val loginService by lazy { FakeLoginService() }
    override val authInfoRepo by lazy { FakeAuthInfoRepo() }
    override val messageBoardService by lazy { FakeMessageBoardService() }
}