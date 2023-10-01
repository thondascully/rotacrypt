package rotacrypt

import kotlin.test.Test
import kotlin.test.assertNotNull

class AppTest {
    @Test fun appHasAGreeting() {
        val classUnderTest = Main()
        assertNotNull(classUnderTest.greeting, "app should have a greeting")
    }
}
