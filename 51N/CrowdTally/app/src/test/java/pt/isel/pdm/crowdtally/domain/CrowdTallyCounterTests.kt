package pt.isel.pdm.crowdtally.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class CrowdTallyCounterTests {
    @Test
    fun `CrowdTallyCounter should increment`() {
        // arrange
        var counter = CrowdTallyCounter(0, 5)
        // act
        counter = counter.increment()

        // assert
        assertEquals(1, counter.currentCrowd)


    }
}