package quaverapp.quaver.dao

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import quaverapp.quaver.IntegrationTest

class SongDaoTest : IntegrationTest() {
    @Autowired
    private lateinit var songDao: SongDao

    @Test
    fun `test fetchAllSongs should return all songs`() {
        // Arrange
        val song = createSong()

        // Act
        val result = songDao.getAllSongs()

        // Assert
        assertThat(result)
            .isEqualTo(listOf(song))
    }

    @Test
    fun `test getSongByArtistId should return songs for the given artist`() {
        // Arrange
        val song = createSong()

        // Act
        val result = songDao.getSongByArtistId(song.artists.first().id)

        // Assert
        assertThat(result)
            .isEqualTo(listOf(song))
    }

    @Test
    fun `test getSongFromAlbumById should return songs for the given album`() {
        //Arrange
        val song = createSong()

        // Act
        val result = songDao.getSongFromAlbumById(song.album.id)

        // Assert
        assertThat(result)
            .isEqualTo(listOf(song))
    }
}
