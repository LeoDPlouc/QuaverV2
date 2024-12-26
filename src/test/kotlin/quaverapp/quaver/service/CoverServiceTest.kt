package quaverapp.quaver.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchException
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import quaverapp.quaver.UnitTest
import quaverapp.quaver.dao.CoverDao
import quaverapp.quaver.model.Cover
import quaverapp.quaver.service.exception.CoverNotFoundException

class CoverServiceTest : UnitTest() {

    @Mock
    lateinit var coverDao: CoverDao

    @InjectMocks
    lateinit var coverService: CoverService

    @Test
    fun `getById should return cover when cover is found`() {
        // Arrange
        val expectedCover = createCover()
        `when`(coverDao.getById(1)).thenReturn(expectedCover)

        // Act
        val result = coverService.getById(1)

        // Assert
        assertThat(result)
            .isEqualTo(expectedCover)
    }

    @Test
    fun `getById should throw CoverNotFoundException when cover is not found`() {
        // Arrange
        `when`(coverDao.getById(1)).thenReturn(null)

        // Act
        val thrown = catchException { coverService.getById(1) }

        // Assert
        assertThat(thrown)
            .isInstanceOf(CoverNotFoundException::class.java)
    }
}
