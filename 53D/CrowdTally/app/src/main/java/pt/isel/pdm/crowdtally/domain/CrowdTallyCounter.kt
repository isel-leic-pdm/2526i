package pt.isel.pdm.crowdtally.domain

data class CrowdTallyCounter(
    val current: Int,
    val max: Int
) {
    val isFull
        get() = current >= max

    val isEmpty
        get() = current <= 0

    companion object {
        val Default = CrowdTallyCounter(0, 5)
    }
}

fun CrowdTallyCounter.increment() = CrowdTallyCounter(current + 1, max)
fun CrowdTallyCounter.decrement() = this.copy(current = current - 1)
fun CrowdTallyCounter.withNewMax(newMax: Int) = CrowdTallyCounter(0, newMax)
