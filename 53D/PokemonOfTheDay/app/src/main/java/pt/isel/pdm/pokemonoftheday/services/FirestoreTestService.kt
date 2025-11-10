package pt.isel.pdm.pokemonoftheday.services


import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import pt.isel.pdm.pokemonoftheday.domain.SimpleModel
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirestoreTestService(
    private val db: FirebaseFirestore
) {

    private val collectionName = "testCollection"


    suspend fun create(m: SimpleModel) {
        db.collection(collectionName)
            .document(m.id)
            .set(simpleModelToHashMap(m))
            //.set(m) //serialized by lib
            .await()

    }

    suspend fun update(m: SimpleModel) {
        db.collection(collectionName)
            .document(m.id)
            .update(simpleModelToHashMap(m)) //fails if doest exist
            .await()
    }

    suspend fun delete(m: SimpleModel) {
        db.collection(collectionName)
            .document(m.id)
            .delete()
            .await()
    }

    suspend fun getAll(): List<SimpleModel> {

        return db.collection(collectionName)
            .get()
            .await()
            .map {
                documentToSimpleModel(it)
            }
    }

    suspend fun getById(id: String): SimpleModel {
        val doc = db.collection(collectionName)
            .document(id)
            .get()
            .await()
        return documentToSimpleModel(doc)
    }

    suspend fun getInstancesWithNumberBiggerThan(nr: Int): List<SimpleModel> {
        return db.collection(collectionName)
            .whereGreaterThanOrEqualTo(NUMBER_LABEL, nr)
            .orderBy(NUMBER_LABEL)
            .limit(10)
            .get()
            .await()
            .map {
                documentToSimpleModel(it)
            }
    }

    suspend fun waitForChanges(id: String): SimpleModel {
        return suspendCancellableCoroutine() { continuation ->

            var listener: ListenerRegistration? = null
            listener = db.collection(collectionName)
                .document(id)
                .addSnapshotListener { snapshot, error ->
                    try {
                        if (error != null)
                            continuation.resumeWithException(error)
                        else if (snapshot == null)
                            continuation.resumeWithException(Exception("Object with id:$id cannot be found"))
                        else
                            continuation.resume(documentToSimpleModel(snapshot))
                    } finally {
                        listener!!.remove()
                    }
                }

            continuation.invokeOnCancellation {
                listener?.remove()
            }
        }
    }

    fun getByIdFlowV0(id: String): Flow<SimpleModel> {
        return flow {
            while (true) {
                val model = waitForChanges(id)
                emit(model)
            }
        }
    }

    fun getByIdFlowV1(id: String): Flow<SimpleModel> {
        return callbackFlow {
            val listener = db.collection(collectionName)
                .document(id)
                .addSnapshotListener { snapshot, error ->

                    if (error != null)
                        cancel("error", error)
                    else if (snapshot == null)
                        cancel("doc not found", Exception("Object with id:$id cannot be found"))
                    else
                        trySendBlocking(snapshot)

                }

            awaitClose { listener.remove() }
        }.map { documentToSimpleModel(it) }
    }

    fun getByIdFlow(id: String): Flow<SimpleModel> {
        return db
            .collection(collectionName)
            .document(id)
            .snapshots()
            .map { documentToSimpleModel(it) }
    }


    private val NUMBER_LABEL = "Number"
    private val ARRAY_LABEL = "Array"

    private fun simpleModelToHashMap(m: SimpleModel): Map<String, Any> {
        return mapOf(
            NUMBER_LABEL to m.number,
            ARRAY_LABEL to m.array
        )
    }

    private fun documentToSimpleModel(obj: DocumentSnapshot): SimpleModel {

        return SimpleModel(
            id = obj.id,
            number = (obj.data!![NUMBER_LABEL] as Long).toInt(),
            array = (obj.data!![ARRAY_LABEL] as List<Long>).map { it.toInt() }
        )
    }


}