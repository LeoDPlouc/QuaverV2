import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchException
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import quaverapp.quaver.TestSuite
import quaverapp.quaver.dao.AlbumDao
import quaverapp.quaver.model.Album
import quaverapp.quaver.model.Artist
import quaverapp.quaver.model.Cover
import quaverapp.quaver.model.Joining
import quaverapp.quaver.service.AlbumService
import quaverapp.quaver.service.exception.AlbumNotFoundException
import java.time.LocalDateTime

class AlbumServiceTest : TestSuite() {

    @Mock
    private lateinit var albumDao: AlbumDao

    @InjectMocks
    private lateinit var albumService: AlbumService

    @Test
    fun `should return all albums`() {
        // Arrange
        val artist1 = Artist(
            1,
            "Artist 1",
            Cover(1, "tinyUrl1", "smallUrl1", "mediumUrl1", "largeUrl1", "veryLargeUrl1"),
            "mbid1",
            LocalDateTime.now(),
            null
        )
        val artist2 = Artist(
            2,
            "Artist 2",
            Cover(2, "tinyUrl2", "smallUrl2", "mediumUrl2", "largeUrl2", "veryLargeUrl2"),
            "mbid2",
            LocalDateTime.now(),
            null
        )
        val cover = Cover(3, "tinyUrl3", "smallUrl3", "mediumUrl3", "largeUrl3", "veryLargeUrl3")
        val joining = Joining(1, 1, 1, "Featured artist") // Exemple de Joining
        val albums = listOf(
            Album(
                1,
                "Album 1",
                listOf(artist1),
                cover,
                2020,
                "mbid1",
                LocalDateTime.now(),
                null,
                null,
                listOf(joining)
            ),
            Album(2, "Album 2", listOf(artist2), cover, 2021, "mbid2", LocalDateTime.now(), null, null, listOf(joining))
        )
        `when`(albumDao.getAllAlbums()).thenReturn(albums)

        // Act
        val result = albumService.getAllAlbum()

        // Assert
        assertThat(result)
            .isEqualTo(albums)
    }

    @Test
    fun `should return album by id when it exists`() {
        // Arrange
        val artist = Artist(
            1,
            "Artist 1",
            Cover(1, "tinyUrl1", "smallUrl1", "mediumUrl1", "largeUrl1", "veryLargeUrl1"),
            "mbid1",
            LocalDateTime.now(),
            null
        )
        val cover = Cover(2, "tinyUrl2", "smallUrl2", "mediumUrl2", "largeUrl2", "veryLargeUrl2")
        val joining = Joining(1, 1, 1, "Featured artist") // Exemple de Joining
        val album =
            Album(1, "Album 1", listOf(artist), cover, 2020, "mbid1", LocalDateTime.now(), null, null, listOf(joining))
        `when`(albumDao.getAlbumById(1)).thenReturn(album)

        // Act
        val result = albumService.getAlbumById(1)

        // Assert
        assertThat(result)
            .isEqualTo(album)
    }

    @Test
    fun `should throw AlbumNotFoundException when album does not exist by id`() {
        // Arrange
        `when`(albumDao.getAlbumById(1)).thenReturn(null)

        // Act
        val thrown = catchException { albumService.getAlbumById(1) }

        // Assert
        assertThat(thrown).isInstanceOf(AlbumNotFoundException::class.java)
    }

    @Test
    fun `should return albums by artist id`() {
        // Arrange
        val artist1 = Artist(
            1,
            "Artist 1",
            Cover(1, "tinyUrl1", "smallUrl1", "mediumUrl1", "largeUrl1", "veryLargeUrl1"),
            "mbid1",
            LocalDateTime.now(),
            null
        )
        val artist2 = Artist(
            1,
            "Artist 1",
            Cover(2, "tinyUrl2", "smallUrl2", "mediumUrl2", "largeUrl2", "veryLargeUrl2"),
            "mbid2",
            LocalDateTime.now(),
            null
        )
        val cover = Cover(3, "tinyUrl3", "smallUrl3", "mediumUrl3", "largeUrl3", "veryLargeUrl3")
        val joining = Joining(1, 1, 1, "Featured artist")
        val albums = listOf(
            Album(
                1,
                "Album 1",
                listOf(artist1),
                cover,
                2020,
                "mbid1",
                LocalDateTime.now(),
                null,
                null,
                listOf(joining)
            ),
            Album(2, "Album 2", listOf(artist2), cover, 2021, "mbid2", LocalDateTime.now(), null, null, listOf(joining))
        )
        `when`(albumDao.getAlbumsByArtistId(1)).thenReturn(albums)

        // Act
        val result = albumService.getAlbumsByArtistId(1)

        // Assert
        assertThat(result)
            .isEqualTo(albums)
    }
}
