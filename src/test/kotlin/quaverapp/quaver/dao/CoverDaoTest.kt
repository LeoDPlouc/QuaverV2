package quaverapp.quaver.dao

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import quaverapp.quaver.IntegrationTest
import kotlin.test.assertNull

class CoverDaoTest : IntegrationTest() {

    @Autowired
    lateinit var coverDao: CoverDao


    @Test
    fun `test getById returns a cover when found`() {
        // Arrange
        val cover = createCover()

        // Act
        val result = coverDao.getById(cover.id)

        // Assert
        Assertions.assertThat(result)
            .isEqualTo(cover)
    }

    @Test
    fun `test getById returns null when not found`() {
        // Act
        val result = coverDao.getById(999)

        // Assert
        assertNull(result)
    }
}
