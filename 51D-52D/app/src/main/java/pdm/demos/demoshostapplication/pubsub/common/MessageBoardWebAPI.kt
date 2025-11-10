package pdm.demos.demoshostapplication.pubsub.common

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MessageBoardWebAPI: MessageBoardService {
    override suspend fun publish(board: MessageBoard) {
        TODO()
    }

    override fun subscribeAll(): Flow<List<MessageBoard>> = flow {
        while (true) {
            delay(10000)
            // fetch data from web API
            // emit(theDataFromTheWebAPI)
        }
    }
}