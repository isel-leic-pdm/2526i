package pdm.demos.demoshostapplication.pubsub.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


/**
 * Service interface for publishing and subscribing to message boards.
 */
interface MessageBoardService {

    /**
     * Publishes a message board.
     */
    suspend fun publish(board: MessageBoard)

    /**
     * Subscribes to observe the data set comprised of all the message boards.
     */
    fun subscribeAll(): Flow<List<MessageBoard>>
}

/**
 * A data class representing a message board. A message board is comprised of a non-blank identifier
 * @param boardId The identifier for the message board.
 * @param message The message content on the board.
 */
data class MessageBoard(val boardId: String, val message: String? = null) {
    init {
        require(boardId.isNotBlank())
    }
}

/**
 * A fake implementation of the MessageBoardService for testing and development purposes.
 */
class FakeMessageBoardService : MessageBoardService {

    private val boardsFlow = MutableStateFlow(listOf(
        MessageBoard(boardId = "Board 1", message = "This is a very long message to test the UI rendering capabilities of the message board view component."),
        MessageBoard(boardId = "Board 2", message = "Another long message to see how the text wrapping and layout behaves in different scenarios."),
        MessageBoard(boardId = "Board 3", message = "A not so long message."),
    ))

    override suspend fun publish(board: MessageBoard) {
        val newBoardsList = boardsFlow.value + board
        boardsFlow.emit(newBoardsList)
    }

    override fun subscribeAll(): Flow<List<MessageBoard>> = boardsFlow.asStateFlow()
}