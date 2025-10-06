package pt.isel.pdm.crowdtally.domain

import junit.framework.TestCase.assertEquals
import org.junit.Test

class CrowdTallyCounterTests {

    @Test
    fun `should increment`() {
        //  arrange
        var counter = CrowdTallyCounter(0, 5)

        //  act
        counter = counter.increment()

        //  assert
        assertEquals(1, counter.current)
    }
}