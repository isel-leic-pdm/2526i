package pdm.demos.demoshostapplication.pubsub.publish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pdm.demos.demoshostapplication.pubsub.common.MessageBoard
import pdm.demos.demoshostapplication.pubsub.common.MessageBoardService


/**
 * Represents the possible states of the publish screen.
 */
interface PublishScreenState {
    object Idle : PublishScreenState
    data class Publishing(val message: MessageBoard) : PublishScreenState
    data class PublishError(val error: Exception) : PublishScreenState
}

/**
 * ViewModel for the screen used to publish messages to a message board of the pub-sub demo
 * application.
 * @param service The service to be used to publish messages to the message board.
 */
class PublishScreenViewModel(private val service: MessageBoardService) : ViewModel() {

    companion object {
        /**
         * Returns a factory to create a [PublishScreenViewModel] with the provided
         * [MessageBoardService].
         * @param service The service to be used to get the available message boards.
         * Design challenge: Should we also pass here the use case function? Why? Why not?
         */
        fun getFactory(service: MessageBoardService) = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T =
                if (modelClass.isAssignableFrom(PublishScreenViewModel::class.java)) {
                    PublishScreenViewModel(service = service) as T
                }
                else throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    private val _screenState by lazy {
        MutableStateFlow<PublishScreenState>(PublishScreenState.Idle)
    }
    val screenState = _screenState.asStateFlow()

    /**
     * Publishes a message to the specified message board.
     * @param messageBoard The message and message board info to use to publish the message.
     */
    fun publishMessage(messageBoard: MessageBoard) {

        if (_screenState.value is PublishScreenState.Publishing) return

        _screenState.value = PublishScreenState.Publishing(messageBoard)
        viewModelScope.launch {
            try {
                service.publish(messageBoard)
                _screenState.emit(PublishScreenState.Idle)
            } catch (e: Exception) {
                _screenState.emit(PublishScreenState.PublishError(error = e))
            }
        }
    }
}