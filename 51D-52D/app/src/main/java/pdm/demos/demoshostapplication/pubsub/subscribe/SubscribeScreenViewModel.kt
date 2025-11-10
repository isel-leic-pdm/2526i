package pdm.demos.demoshostapplication.pubsub.subscribe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.Flow
import pdm.demos.demoshostapplication.pubsub.common.MessageBoard
import pdm.demos.demoshostapplication.pubsub.common.MessageBoardService

/**
 * ViewModel for the screen used to subscribe to message boards of the pub-sub demo
 * application.
 * @param service The service to be used to get the available message boards.
 *
 * DESIGN NOTE: This screen is so simple that I have not used the usual approach of designing it as
 * a state machine. This is because the only state we need to represent is the list of message
 * boards, which is directly provided by the service as a Flow.
 */
class SubscribeScreenViewModel(private val service: MessageBoardService) : ViewModel() {

    companion object {
        /**
         * Returns a factory to create a [SubscribeScreenViewModel] with the provided
         * [MessageBoardService].
         * @param service The service to be used to get the available message boards.
         * Design challenge: Should we also pass here the use case function? Why? Why not?
         */
        fun getFactory(service: MessageBoardService) = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                if (modelClass.isAssignableFrom(SubscribeScreenViewModel::class.java)) {
                    SubscribeScreenViewModel(service = service) as T
                }
                else throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    /**
     * The flow of the list of all available message boards.
     */
    val messageBoards: Flow<List<MessageBoard>> by lazy { service.subscribeAll() }
}