package quaverapp.quaver.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import quaverapp.quaver.UnitTest
import quaverapp.quaver.dao.SongDao
import quaverapp.quaver.model.*
import java.time.LocalDateTime

class SongServiceTest : UnitTest() {

    @Mock
    lateinit var songDao: SongDao

    @InjectMocks
    lateinit var songService: SongService

    @Test
    fun `should return all songs`() {
        // Arrange
        val songList = listOf(
            createSong(),
            createSong()
        )
        `when`(songDao.getAllSongs()).thenReturn(songList)

        // Act
        val result = songService.getAllSongs()

        // Assert
        assertThat(result)
            .isEqualTo(songList)
        verify(songDao).getAllSongs()
    }

    @Test
    fun `should return songs by artist id`() {
        // Arrange
        val songList = listOf(
            createSong(),
            createSong()
        )
        `when`(songDao.getSongByArtistId(1)).thenReturn(songList)

        // Act
        val result = songService.getSongByArtistId(1)

        // Assert
        assertThat(result)
            .isEqualTo(songList)
        verify(songDao).getSongByArtistId(1)
    }

    @Test
    fun `should return songs by album id`() {
        // Arrange
        val songList = listOf(
            createSong(),
            createSong()
        )
        `when`(songDao.getSongFromAlbumById(1)).thenReturn(songList)

        // Act
        val result = songService.getSongByAlbumId(1)

        // Assert
        assertThat(result)
            .isEqualTo(songList)
        verify(songDao).getSongFromAlbumById(1)
    }
}
