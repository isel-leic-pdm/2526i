package pdm.demos.demoshostapplication.crowdtally.domain

const val DEFAULT_CAPACITY = 1000

/**
 * A counter of the number of people in a venue of limited capacity. Instances are immutable.
 * @property value The current number of people. Must be between 0 and [capacity].
 * @property capacity The maximum number of people allowed in the venue. Must be positive.
 */
data class CrowdCounter(
    val value: Int = 0,
    val capacity: Int = DEFAULT_CAPACITY
) {
    init {
        require(value = isValidCapacity(capacity)) { "Capacity must be positive" }
        require(value = value in 0..capacity) { "Value must be between 0 and capacity" }
    }

    /**
     * Increments the counter by one.
     * @return a new counter with the incremented value.
     */
    operator fun inc(): CrowdCounter = if (isNotAtMaximum()) copy(value = value + 1) else this

    /**
     * Decrements the counter by one.
     * @return a new counter with the decremented value.
     */
    operator fun dec(): CrowdCounter = if (isNotAtMinimum()) copy(value = value - 1) else this

    /**
     * @return `true` if the counter is not at the minimum value, `false` otherwise.
     */
    fun isNotAtMinimum(): Boolean = value > 0

    /**
     * @return `true` if the counter is not at the maximum value, `false` otherwise.
     */
    fun isNotAtMaximum(): Boolean = value < capacity

    companion object {
        /**
         * Checks if the given capacity is acceptable according to the domain rules.
         * @param capacity The capacity to check.
         * @return `true` if the capacity is valid, `false` otherwise.
         */
        fun isValidCapacity(capacity: Int): Boolean = capacity > 0
    }
}

/**
 * Extension function to check if an integer is a valid capacity for [CrowdCounter].
 * @receiver The integer to check.
 * @return `true` if the integer is a valid capacity, `false` otherwise.
 */
fun Int.isValidCapacity(): Boolean = CrowdCounter.isValidCapacity(capacity = this)
