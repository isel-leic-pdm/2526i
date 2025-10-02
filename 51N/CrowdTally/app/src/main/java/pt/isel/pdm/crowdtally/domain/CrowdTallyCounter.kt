package pt.isel.pdm.crowdtally.domain

data class CrowdTallyCounter(
    val currentCrowd: Int,
    val maxCrowd: Int
) {

    companion object
    {
        val Default = CrowdTallyCounter(0, 5)
    }

    val isFull
        get() = currentCrowd >= maxCrowd

    val isEmpty
        get() = currentCrowd <= 0
}


fun CrowdTallyCounter.increment() =
    CrowdTallyCounter(currentCrowd + 1, maxCrowd)

fun CrowdTallyCounter.decrement() =
    this.copy(currentCrowd = currentCrowd - 1)

fun CrowdTallyCounter.changeMaxCrowd(maxCrowd: Int) =
    this.copy(maxCrowd = maxCrowd)

