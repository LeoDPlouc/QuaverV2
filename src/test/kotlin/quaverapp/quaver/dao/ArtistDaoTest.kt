package quaverapp.quaver.dao

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import quaverapp.quaver.IntegrationTest

class ArtistDaoTest : IntegrationTest() {

    @Autowired
    lateinit var artistDao: ArtistDao

    @Test
    fun `test getAllArtists returns a list of artists`() {
        // Arrange
        val artist = createArtist()

        // Act
        val result = artistDao.getAllArtists()

        // Assert
        assertThat(result)
            .isEqualTo(listOf(artist))
    }

    @Test
    fun `test getArtistById returns an artist when found`() {
        // Arrange
        val artist = createArtist()

        // Act
        val result = artistDao.getArtistById(artist.id)

        // Assert
        assertThat(result)
            .isEqualTo(artist)
    }

    @Test
    fun `test getArtistById returns null when not found`() {
        // Act
        val result = artistDao.getArtistById(999)

        // Assert
        assertThat(result).isNull()
    }
}
