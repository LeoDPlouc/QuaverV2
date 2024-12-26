package quaverapp.quaver.dao

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import quaverapp.quaver.IntegrationTest

class AlbumDaoTest : IntegrationTest() {

    @Autowired
    private lateinit var albumDao: AlbumDao

    @Test
    fun `test getAllAlbums returns a list of albums with cover and artist`() {
        // Arrange
        val album = createAlbum()

        // Act
        val result = albumDao.getAllAlbums()

        // Assert
        assertThat(result)
            .isEqualTo(listOf(album))
    }

    @Test
    fun `test getAlbumById returns an album with cover and artist when found`() {
        // Arrange
        val album = createAlbum()

        // Act
        val result = albumDao.getAlbumById(album.id)

        // Assert
        assertThat(result)
            .isEqualTo(album)
    }

    @Test
    fun `test getAlbumById returns null when album not found`() {
        // Act
        val result = albumDao.getAlbumById(999)

        // Assert
        assertThat(result).isNull()
    }

    @Test
    fun `test getAlbumsByArtistId returns albums related to an artist`() {
        // Arrange
        val album = createAlbum()

        // Act
        val result = albumDao.getAlbumsByArtistId(album.artists.first().id)

        // Assert
        assertThat(result)
            .isEqualTo(listOf(album))
    }
}
