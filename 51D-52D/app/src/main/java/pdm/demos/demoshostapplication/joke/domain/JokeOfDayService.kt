package pdm.demos.demoshostapplication.joke.domain


/**
 * Service to get the joke of the day.
 * An abstraction to be implemented by different approaches using different
 * data sources.
 */
interface JokeOfDayService {

    fun getJokeOfTheDay(): Joke
}

/**
 * A joke with its content and source.
 */
data class Joke(
    val content: String,
    val source: String
)

class FakeJokeOfDayService : JokeOfDayService {
    override fun getJokeOfTheDay(): Joke {
        return Joke(
            content = "The quickest way to a man's heart is with Chuck Norris's fist.",
            source = "https://api.chucknorris.io"
        )
    }
}
