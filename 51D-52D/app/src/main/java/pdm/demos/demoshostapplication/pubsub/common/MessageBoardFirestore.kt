package pdm.demos.demoshostapplication.pubsub.common

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

private const val MESSAGE_BOARDS_COLLECTION = "message_boards"

/**
 * Firestore implementation of the MessageBoardService.
 */
class MessageBoardFirestore(private val firestoreDb: FirebaseFirestore) : MessageBoardService {

    override suspend fun publish(board: MessageBoard) {
        firestoreDb.collection(MESSAGE_BOARDS_COLLECTION)
            .document(board.boardId)
            .set(board)
            .await()
    }

    override fun subscribeAll(): Flow<List<MessageBoard>> = firestoreDb
        .collection(MESSAGE_BOARDS_COLLECTION)
        .snapshots()
        .map { snapshot -> snapshot.toMessageBoardList() }
}

private const val BOARD_ID_FIELD = "boardId"
private const val MESSAGE_FIELD = "message"

/**
 * [MessageBoard] extension function used to convert an instance to a map of key-value
 * pairs containing the object's properties
 */
fun MessageBoard.toDocumentContent() = mapOf(
    BOARD_ID_FIELD to boardId,
    MESSAGE_FIELD to message,
)

/**
 * Converts a QuerySnapshot with a list of documents to a list of MessageBoard objects.
 */
fun QuerySnapshot.toMessageBoardList(): List<MessageBoard> =
    documents.mapNotNull { it.toMessageBoard() }

/**
 * Converts a DocumentSnapshot to a MessageBoard object.
 */
fun DocumentSnapshot.toMessageBoard(): MessageBoard =
    MessageBoard(
        boardId = id,
        message = getString(MESSAGE_FIELD)
    )