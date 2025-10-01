package pdm.demos.demoshostapplication.joke.domain

interface JokeOfDayService {

    suspend fun getJokeOfTheDay(): Joke
}

data class Joke(
    val content: String,
    val source: String
)

