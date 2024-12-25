package quaverapp.quaver.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import quaverapp.quaver.TestSuite
import quaverapp.quaver.dao.SongDao
import quaverapp.quaver.model.*
import java.time.LocalDateTime

class SongServiceTest : TestSuite() {

    @Mock
    lateinit var songDao: SongDao

    @InjectMocks
    lateinit var songService: SongService

    private fun createTestSong(id: Int): Song {
        val cover = Cover(
            id = id,
            tinyUrl = "http://example.com/tiny.jpg",
            smallUrl = "http://example.com/small.jpg",
            mediumUrl = "http://example.com/medium.jpg",
            largeUrl = "http://example.com/large.jpg",
            veryLargeUrl = "http://example.com/verylarge.jpg"
        )

        val artist = Artist(
            id = id,
            name = "Artist",
            cover = cover,
            mbid = "mbidArtist",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        )

        val joining = Joining(
            id = id,
            n = id,
            artistId = artist.id,
            joinphrase = "Joined as featured artist"
        )

        val album = Album(
            id = id,
            title = "Album",
            artists = listOf(artist),
            cover = cover,
            year = 2020,
            mbid = "mbidAlbum",
            createdAt = LocalDateTime.of(2000, 2, 2, 0, 0, 0),
            updatedAt = LocalDateTime.of(2000, 2, 2, 0, 0, 0),
            coverUpdatedAt = LocalDateTime.of(2000, 2, 2, 0, 0, 0),
            joinings = listOf(joining)
        )

        return Song(
            id = id,
            title = "Song",
            n = 1,
            duration = 200,
            like = Like.Nothing,
            artists = listOf(artist),
            album = album,
            path = "/path/to/song",
            acoustid = "acoustid",
            year = 2020,
            format = "MP3",
            mbid = "mbid",
            createdAt = LocalDateTime.of(2000, 2, 2, 0, 0, 0),
            lastUpdated = LocalDateTime.of(2000, 2, 2, 0, 0, 0),
            joinings = listOf(joining)
        )
    }

    @Test
    fun `should return all songs`() {
        // Given
        val song1 = createTestSong(1)
        val song2 = createTestSong(2)
        val songList = listOf(song1, song2)
        `when`(songDao.getAllSongs()).thenReturn(songList)

        // When
        val result = songService.getAllSongs()

        // Then
        assertThat(result)
            .isEqualTo(songList)
        verify(songDao).getAllSongs()
    }

    @Test
    fun `should return songs by artist id`() {
        // Given
        val song1 = createTestSong(1)
        val song2 = createTestSong(2)
        val songList = listOf(song1, song2)
        `when`(songDao.getSongByArtistId(1)).thenReturn(songList)

        // When
        val result = songService.getSongByArtistId(1)

        // Then
        assertThat(result)
            .isEqualTo(songList)
        verify(songDao).getSongByArtistId(1)
    }

    @Test
    fun `should return songs by album id`() {
        // Given
        val song1 = createTestSong(1)
        val song2 = createTestSong(2)
        val songList = listOf(song1, song2)
        `when`(songDao.getSongFromAlbumById(1)).thenReturn(songList)

        // When
        val result = songService.getSongByAlbumId(1)

        // Then
        assertThat(result)
            .isEqualTo(songList)
        verify(songDao).getSongFromAlbumById(1)
    }
}
