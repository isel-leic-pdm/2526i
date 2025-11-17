package pdm.demos.demoshostapplication.joke.reactive

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SuspendingLatch {

    private var isOpen = false
    private var waiters = mutableListOf<Continuation<Unit>>()
    private val guard = Mutex()

    suspend fun open() {
        val toResume = guard.withLock {
            if (isOpen) return
            isOpen = true

            val theWaiters = waiters
            waiters = mutableListOf()
            theWaiters
        }

        toResume.forEach { it.resume(Unit) }
    }

    suspend fun await() {
        guard.lock()
        if (isOpen) { guard.unlock(); return }
        suspendCoroutine { continuation ->
            waiters.add(continuation)
            guard.unlock()
        }
    }
}