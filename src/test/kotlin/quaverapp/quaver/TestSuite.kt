package quaverapp.quaver

import org.junit.jupiter.api.BeforeEach
import org.mockito.MockitoAnnotations

abstract class TestSuite {
    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }
}