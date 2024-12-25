package quaverapp.quaver.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchException
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import quaverapp.quaver.TestSuite
import quaverapp.quaver.dao.CoverDao
import quaverapp.quaver.model.Cover
import quaverapp.quaver.service.exception.CoverNotFoundException

class CoverServiceTest : TestSuite() {

    @Mock
    lateinit var coverDao: CoverDao

    @InjectMocks
    lateinit var coverService: CoverService

    @Test
    fun `getById should return cover when cover is found`() {
        // Arrange
        val coverId = 1
        val expectedCover = Cover(
            id = coverId,
            tinyUrl = "http://example.com/tiny.jpg",
            smallUrl = "http://example.com/small.jpg",
            mediumUrl = "http://example.com/medium.jpg",
            largeUrl = "http://example.com/large.jpg",
            veryLargeUrl = "http://example.com/verylarge.jpg"
        )
        `when`(coverDao.getById(coverId)).thenReturn(expectedCover)

        // Act
        val result = coverService.getById(coverId)

        // Assert
        assertThat(result)
            .isEqualTo(expectedCover)
    }

    @Test
    fun `getById should throw CoverNotFoundException when cover is not found`() {
        // Arrange
        val coverId = 1
        `when`(coverDao.getById(coverId)).thenReturn(null)

        // Act
        var thrown = catchException { coverService.getById(coverId) }

        // Assert
        assertThat(thrown)
            .isInstanceOf(CoverNotFoundException::class.java)
    }
}
