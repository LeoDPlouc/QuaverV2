package quaverapp.quaver.service

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import quaverapp.quaver.TestSuite
import quaverapp.quaver.dao.ArtistDao
import quaverapp.quaver.model.Artist
import quaverapp.quaver.model.Cover
import quaverapp.quaver.service.exception.ArtistNotFoundException
import java.time.LocalDateTime

class ArtistServiceTest : TestSuite() {

    @Mock
    lateinit var artistDao: ArtistDao

    @InjectMocks
    lateinit var artistService: ArtistService

    @Test
    fun `should return list of artists`() {
        // Arrange
        val cover1 = Cover(
            id = 1,
            tinyUrl = "tiny_cover1.jpg",
            smallUrl = "small_cover1.jpg",
            mediumUrl = "medium_cover1.jpg",
            largeUrl = "large_cover1.jpg",
            veryLargeUrl = "very_large_cover1.jpg"
        )
        val cover2 = Cover(
            id = 2,
            tinyUrl = "tiny_cover2.jpg",
            smallUrl = "small_cover2.jpg",
            mediumUrl = "medium_cover2.jpg",
            largeUrl = "large_cover2.jpg",
            veryLargeUrl = "very_large_cover2.jpg"
        )

        val artistList = listOf(
            Artist(1, "Artist 1", cover1, "mbid1", LocalDateTime.now(), LocalDateTime.now()),
            Artist(2, "Artist 2", cover2, "mbid2", LocalDateTime.now(), LocalDateTime.now())
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
        val cover = Cover(
            id = 1,
            tinyUrl = "tiny_cover1.jpg",
            smallUrl = "small_cover1.jpg",
            mediumUrl = "medium_cover1.jpg",
            largeUrl = "large_cover1.jpg",
            veryLargeUrl = "very_large_cover1.jpg"
        )

        val artist = Artist(
            id = 1,
            name = "Artist 1",
            cover = cover,
            mbid = "mbid1",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

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
