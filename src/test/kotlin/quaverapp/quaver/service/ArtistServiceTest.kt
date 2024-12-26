package quaverapp.quaver.service

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import quaverapp.quaver.UnitTest
import quaverapp.quaver.dao.ArtistDao
import quaverapp.quaver.model.Artist
import quaverapp.quaver.model.Cover
import quaverapp.quaver.service.exception.ArtistNotFoundException
import java.time.LocalDateTime

class ArtistServiceTest : UnitTest() {

    @Mock
    lateinit var artistDao: ArtistDao

    @InjectMocks
    lateinit var artistService: ArtistService

    @Test
    fun `should return list of artists`() {
        // Arrange
        val artistList = listOf(
            createArtist(),
            createArtist()
        )

        `when`(artistDao.getAllArtists()).thenReturn(artistList)

        // Act
        val result = artistService.getAllArtists()

        // Assert
        assertThat(result)
            .isEqualTo(artistList)
    }

    @Test
    fun `should return an artist by id`() {
        // Arrange
        val artist = createArtist()

        `when`(artistDao.getArtistById(1)).thenReturn(artist)

        // Act
        val result = artistService.getArtistById(1)

        // Assert
        assertThat(result)
            .isEqualTo(artist)
    }

    @Test
    fun `should throw ArtistNotFoundException when artist not found`() {
        // Arrange
        `when`(artistDao.getArtistById(999)).thenReturn(null)

        // Act
        val thrown = Assertions.catchException { artistService.getArtistById(999) }

        // Assert
        assertThat(thrown).isInstanceOf(ArtistNotFoundException::class.java)
    }
}
